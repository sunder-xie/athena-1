package com.tqmall.athena.redisBiz.maintain;

import com.google.common.collect.Lists;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.maintain.MaintainItemDOMapper;
import com.tqmall.athena.dal.mapper.maintain.ModelMaintainRelationDOMapper;
import com.tqmall.athena.redisBiz.car.CarRedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huangzhangting on 15/11/7.
 */
@Component
public class MaintainRedisManagerImpl implements MaintainRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private MaintainItemDOMapper itemMapper;

    @Autowired
    private ModelMaintainRelationDOMapper relationMapper;

    @Autowired
    private CarRedisManager carRedisManager;


    @Override
    public List<MaintainItemDO> getItems(){
        List<MaintainItemDO> itemList;
        String itemStr = redisClient.get(RedisKeyBean.MAINTAIN_ITEM_KEY);
        if(itemStr!=null){
            itemList = JsonUtil.jacksonToCollection(itemStr, List.class, MaintainItemDO.class);
        }else{
            itemList = itemMapper.selectItems();
            if(!itemList.isEmpty()){
                redisClient.setStringWithTime(RedisKeyBean.MAINTAIN_ITEM_KEY,
                        JsonUtil.objectToJsonStr(itemList), RedisKeyBean.RREDIS_EXP_DAY);
            }
        }
        return itemList;
    }

    @Override
    public List<Integer> getMiles(Integer carId){

        String key = String.format("%s%d", RedisKeyBean.MAINTAIN_MILE_PREFIX, carId);
        String mileStr = redisClient.get(key);

        if(redisClient.isNone(mileStr)){
            return new ArrayList<>();
        }

        if(mileStr != null){
            return JsonUtil.jacksonToCollection(mileStr, List.class, Integer.class);
        }

        List<Integer> mileList = relationMapper.selectMiles(carId);
        if(mileList.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.setStringWithTime(key,
                    JsonUtil.objectToJsonStr(mileList), RedisKeyBean.RREDIS_EXP_DAY);
        }

        return mileList;
    }

    @Override
    public List<MaintainDetail> getMaintainDetail(Integer carId){

        String key = String.format(RedisKeyBean.MAINTAIN_DETAIL_CAR_ID, carId);
        String detailStr = redisClient.get(key);

        if(redisClient.isNone(detailStr)){
            return new ArrayList<>();
        }

        if(detailStr != null){
            return JsonUtil.jacksonToCollection(detailStr, List.class, MaintainDetail.class);
        }

        List<MaintainDetail> detailList = relationMapper.selectMaintainDetail(carId, null);
        if(detailList.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.setStringWithTime(key,
                    JsonUtil.objectToJsonStr(detailList), RedisKeyBean.RREDIS_EXP_DAY);
        }

        return detailList;
    }

    @Override
    public List<MaintainDetail> getMaintainDetailByYearId(Integer yearId){

        String key = String.format(RedisKeyBean.MAINTAIN_DETAIL_YEAR_ID, yearId);
        String dataStr = redisClient.get(key);

        if(redisClient.isNone(dataStr)){
            return Lists.newArrayList();
        }

        if(dataStr != null){
            return JsonUtil.jacksonToCollection(dataStr, List.class, MaintainDetail.class);
        }

        List<CarCategoryDO> carCategoryDOs = carRedisManager.getCarListByPid(yearId);
        if(CollectionUtils.isEmpty(carCategoryDOs)){
            redisClient.setNone(key);
            return Lists.newArrayList();
        }

        Collections.sort(carCategoryDOs, new Comparator<CarCategoryDO>() {
            @Override
            public int compare(CarCategoryDO o1, CarCategoryDO o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        List<MaintainDetail> maintainDetails = null;
        for(CarCategoryDO car : carCategoryDOs){
            maintainDetails = getMaintainDetail(car.getId());
            if(!maintainDetails.isEmpty()){
                redisClient.setStringWithTime(key, JsonUtil.objectToJsonStr(maintainDetails),
                        RedisKeyBean.RREDIS_EXP_DAY);
                break;
            }
        }

        if(CollectionUtils.isEmpty(maintainDetails)){
            redisClient.setNone(key);
        }

        return maintainDetails;
    }


    @Override
    public List<Integer> getMaintainCarIdsByCarId(Integer carId, Integer level){
        String key = String.format(RedisKeyBean.MAINTAIN_CAR_IDS_KEY, carId);
        String redisStr = redisClient.get(key);

        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null){
            return JsonUtil.jacksonToCollection(redisStr, List.class, Integer.class);
        }

        List<Integer> list;
        switch (level){
            case 1:
                list = relationMapper.selectCarIdsByBrandId(carId);
                break;
            case 2:
                list = relationMapper.selectCarIdsBySeriesId(carId);
                break;
            case 3:
                list = relationMapper.selectCarIdsByModelId(carId);
                break;
            case 4:
                list = relationMapper.selectCarIdsByPowerId(carId);
                break;
            case 5:
                list = relationMapper.selectCarIdsByYearId(carId);
                break;
            default:
                list = new ArrayList<>();
                break;
        }

        if(list.isEmpty()){
            redisClient.setNone(key);
        }else {
            redisClient.setStringWithTime(key,
                    JsonUtil.objectToJsonStr(list), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<MaintainDetail> getMaintainDetailCommon(Integer carId, Integer maintainCarId){
        String key = String.format(RedisKeyBean.MAINTAIN_DETAIL_PREFIX, carId);
        String redisStr = redisClient.get(key);

        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null){
            return JsonUtil.jacksonToCollection(redisStr, List.class, MaintainDetail.class);
        }

        List<MaintainDetail> detailList = getMaintainDetail(maintainCarId);
        if(detailList.isEmpty()){
            redisClient.setNone(key);
        }else {
            redisClient.setStringWithTime(key,
                    JsonUtil.objectToJsonStr(detailList), RedisKeyBean.RREDIS_EXP_DAY);
        }

        return detailList;
    }
}
