package com.tqmall.athena.bussiness.maintain;

import com.google.common.collect.Lists;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.maintain.AdviseMaintainBO;
import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;
import com.tqmall.athena.redisBiz.car.CarRedisManager;
import com.tqmall.athena.redisBiz.maintain.MaintainRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by huangzhangting on 15/9/20.
 */
@Service
@Slf4j
public class MaintainManagerImpl implements MaintainManager {
    private static final String NEXT_MILE = "nextMile";
    private static final String SHOW_MILE = "showMile";

    @Autowired
    private MaintainRedisManager maintainRedisManager;

    @Autowired
    private CarRedisManager carRedisManager;


    @Override
    public List<MaintainItemDO> findItems(){
        List<MaintainItemDO> list = maintainRedisManager.getItems();
        Collections.sort(list, new Comparator<MaintainItemDO>() {
            @Override
            public int compare(MaintainItemDO o1, MaintainItemDO o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return list;
    }

    @Override
    public List<Integer> findMiles(Integer carId){
        if(carId==null || carId<0){
            return Lists.newArrayList();
        }

        return maintainRedisManager.getMiles(carId);
    }

    /*
    *  根据 车款id、输入里程 查询下次保养里程
    *  carId：必填
    *  mile：必填
    *  返回值
    *       nextMile：循环保养里程
    *       showMile：展示保养里程（输入里程数超过最大值，才有该数据，默认null）
    * */
    private Map<String, Integer> findNextMile(Integer carId, Integer mile){

        List<Integer> mileList = maintainRedisManager.getMiles(carId);

        if(mileList==null || mileList.isEmpty()){//不存在保养里程
            log.info("car maintain miles not exit, carId:"+carId);
            return null;
        }
        boolean exit = true;
        if(!mileList.contains(mile)){
            mileList.add(mile);
            exit = false;
        }

        Collections.sort(mileList);
        int idx = mileList.indexOf(mile);

        Integer maxMile = null;
        int round = 0;
        if(idx==(mileList.size()-1)){
            if(exit){
                maxMile = mile;
            }else {
                maxMile = mileList.get(idx - 1);
            }
            round = mile/maxMile;
            mile = mile%maxMile;

            if(!mileList.contains(mile)){
                mileList.add(mile);
            }

            Collections.sort(mileList);
            idx = mileList.indexOf(mile);
        }

        Integer nextMile = mileList.get(idx+1);

        Map<String, Integer> resultMap = new HashMap<>();

        resultMap.put(NEXT_MILE, nextMile);
        if(maxMile!=null) {
            resultMap.put(SHOW_MILE, round * maxMile + nextMile);
        }

        return resultMap;
    }


    @Override
    public List<MaintainDetail> findMaintainDetail(Integer carId, Integer mile){
        if(carId==null || carId<0){
            return Lists.newArrayList();
        }

        List<MaintainDetail> detailList = maintainRedisManager.getMaintainDetail(carId);

        if(detailList==null || detailList.isEmpty()){
            log.info("car maintain detail not exit, carId:"+carId);
            return Lists.newArrayList();
        }

        if(mile!=null){
            Map<String, Integer> resultMap = this.findNextMile(carId, mile);
            if(resultMap==null){
                log.info("car maintain detail not exit, carId:"+carId);
                return Lists.newArrayList();
            }

            Integer nextMile = resultMap.get(NEXT_MILE);

            Integer showMile = resultMap.get(SHOW_MILE);

            List<MaintainDetail> resultList = Lists.newArrayList();
            if(showMile==null) {
                for (MaintainDetail md : detailList) {
                    if (nextMile.equals(md.getMile())) {
                        resultList.add(md);
                    }
                }
            }else{
                for (MaintainDetail md : detailList) {
                    if (nextMile.equals(md.getMile())) {
                        md.setMile(showMile);
                        resultList.add(md);
                    }
                }
            }
            return resultList;
        }

        return detailList;
    }


    @Override
    public List<MaintainDetail> findMaintainDetailByYearId(Integer yearId){
        if(yearId==null || yearId<0){
            return Lists.newArrayList();
        }

        return maintainRedisManager.getMaintainDetailByYearId(yearId);
    }

    @Override
    public AdviseMaintainBO adviseMaintainByYearId(Integer yearId, Integer mile) {
        if(mile==null || mile<0){
            return null;
        }

        List<MaintainDetail> maintainDetails = this.findMaintainDetailByYearId(yearId);
        if(maintainDetails.isEmpty()){
            log.info("car maintain detail not exit, car yearId:"+yearId);
            return null;
        }

        Map<String, Integer> resultMap = this.findNextMile(maintainDetails.get(0).getModelId(), mile);
        if(resultMap==null){
            log.info("car maintain detail not exit, car yearId:"+yearId);
            return null;
        }

        Integer nextMile = resultMap.get(NEXT_MILE);

        List<String> items = new ArrayList<>();
        for(MaintainDetail md : maintainDetails){
            if(nextMile.equals(md.getMile())){
                items.add(md.getServiceName());
            }
        }

        AdviseMaintainBO maintainBO = new AdviseMaintainBO();
        maintainBO.setYearId(yearId);
        maintainBO.setItems(items);

        Integer showMile = resultMap.get(SHOW_MILE);
        if(showMile==null){
            maintainBO.setMileage(nextMile);
        }else{
            maintainBO.setMileage(showMile);
        }

        return maintainBO;
    }


    /*
    *   根据车型分类id，查找保养表关联车款id
    *   返回值：
    *       null：输入车型分类id错误
    *       0：没有保养数据
    * */
    private Integer getMaintainCarId(Integer carId){
        if(carId==null || carId<0){
            return null;
        }
        CarCategoryDO car = carRedisManager.getCarByPrimaryId(carId);
        if(car==null){
            return null;
        }

        if(car.getLevel()==6){
            return carId;
        }

        return getMinMaintainCarId(maintainRedisManager.getMaintainCarIdsByCarId(carId, car.getLevel()));
    }

    private Integer getMinMaintainCarId(List<Integer> carIdList){
        if(CollectionUtils.isEmpty(carIdList)){
            return 0;
        }
        Collections.sort(carIdList);
        return carIdList.get(0);
    }

    @Override
    public Result<List<MaintainDetail>> findMaintainDetailCommon(Integer carId, Integer mile) {
        Integer maintainCarId = getMaintainCarId(carId);
        if(maintainCarId==null){
            return Result.wrapErrorResult("", "请输入正确的车型分类id，输入carId："+carId);
        }
        if(maintainCarId==0){
            log.info("car maintain detail not exit, carId:"+carId);
            return Result.wrapErrorResult("", "数据缺失补充中，输入carId："+carId);
        }

        List<MaintainDetail> detailList = maintainRedisManager.getMaintainDetailCommon(carId, maintainCarId);
        if(detailList.isEmpty()){
            log.info("car maintain detail not exit, carId:"+carId);
            return Result.wrapErrorResult("", "数据缺失补充中，输入carId：" + carId);
        }

        if(mile != null){
            Map<String, Integer> resultMap = this.findNextMile(maintainCarId, mile);
            if(resultMap==null){
                log.info("car maintain detail not exit, carId:"+carId);
                return Result.wrapErrorResult("", "数据缺失补充中，输入carId：" + carId);
            }

            Integer nextMile = resultMap.get(NEXT_MILE);

            Integer showMile = resultMap.get(SHOW_MILE);

            List<MaintainDetail> resultList = Lists.newArrayList();
            if(showMile==null) {
                for (MaintainDetail md : detailList) {
                    if (nextMile.equals(md.getMile())) {
                        resultList.add(md);
                    }
                }
            }else{
                for (MaintainDetail md : detailList) {
                    if (nextMile.equals(md.getMile())) {
                        md.setMile(showMile);
                        resultList.add(md);
                    }
                }
            }

            return Result.wrapSuccessfulResult(resultList);
        }

        return Result.wrapSuccessfulResult(detailList);
    }
}
