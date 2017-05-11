package com.tqmall.athena.bussiness.car;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tqmall.athena.bean.bizBean.car.CarListBO;
import com.tqmall.athena.bean.bizBean.car.CarListSuit4GoodsBO;
import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.common.ShareConstantsUtil;
import com.tqmall.athena.bean.entity.car.CarAllDO;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.car.GoodsCarDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.car.CarRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by zxg on 15/9/22.
 */

@Service
@Slf4j
public class CarCategoryManagerImpl implements CarCategoryManager {

    @Autowired
    private CarRedisManager carRedisManager;

    @Override
    public CarCategoryDO getCarCategoryByPrimaryId(Integer carId) {
        if (carId == null || carId < 0) {
            return null;
        }
        CarCategoryDO carCategoryDO = carRedisManager.getCarByPrimaryId(carId);
        return carCategoryDO;
    }

    @Override
    public List<CarCategoryDO> getCarCategoryByPid(Integer pid) {
        if (pid == null || pid < 0) {
            return Collections.emptyList();
        }

        List<CarCategoryDO> list = carRedisManager.getCarListByPid(pid);
        if (null == list || list.isEmpty()) {
            log.info("can't find car，pID is：" + pid);
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<CarCategoryDO> getCarParentsByCarId(Integer carId) {
        if (carId == null) {
            return Collections.emptyList();
        }

        List<CarCategoryDO> resultList = Lists.newArrayList();
        Integer pid = carId;
        while (true){
            CarCategoryDO carCategoryDO = carRedisManager.getCarByPrimaryId(pid);
            if (carCategoryDO != null) {
                pid = carCategoryDO.getPid();
                resultList.add(carCategoryDO);
            }else{
                break;
            }
        }
        return resultList;
    }

    @Override
    public CarCategoryDO getCarCatByLId(String liyangId) {
        if (null == liyangId) {
            return null;
        }
        CarAllDO carAllDO = carRedisManager.getCarAllByLiyangId(liyangId);
        if (null == carAllDO) {
            return null;
        }
        Integer onlineCarId = carAllDO.getCarModelsId();
        if(null == onlineCarId){
            return null;
        }
        CarCategoryDO carCategoryDO = carRedisManager.getCarByPrimaryId(onlineCarId);
        if(null == carCategoryDO){
            return null;
        }

        return carCategoryDO;
    }

    @Override
    public List<CarListBO> getCarForOldDataByPid(Integer pId) {
        if (pId == null || pId < 0) {
            return Collections.emptyList();
        }

        /***
         *  太复杂，放缓存
         */
        List<CarListBO> finalResultList = carRedisManager.getCarForOldDataByPid(pId);

        return finalResultList;
    }

    @Override
    public List<CarListSuit4GoodsBO> getCartListByGoodsId(Integer goodsId) {
        if (goodsId == null || goodsId < 0) {
            return Collections.emptyList();
        }
        // 注释掉从电商线上库获得
//        boolean isExistGoods = goodsRedisManager.existsGoods(goodsId);
//        if(!isExistGoods){
//            return null;
//        }
        List<GoodsCarDO> goodsCarList =  carRedisManager.getGoodsCarListByGoodsId(goodsId);

        if(null == goodsCarList){
            return null;
        }
        Map<Integer,Set<Integer>> brandSeriesMap = new HashMap<>();
        Map<Integer,Set<Integer>> seriesModelMap = new HashMap<>();
        Map<Integer,Set<GoodsCarDO>> modelFinalCarDOMap = new HashMap<>();

        Map<Integer,String> modelMap = new HashMap<>();

        for(GoodsCarDO goodsCarDO : goodsCarList){
            //brand
            Integer brandId = goodsCarDO.getCarBrandId();
            Integer seriesId = goodsCarDO.getCarSeriesId();
            Integer modelId = goodsCarDO.getCarModelId();
            String modelName = goodsCarDO.getCarModel();

            String powerName = goodsCarDO.getCarPower();
            String yearName = goodsCarDO.getCarYear();
            String finalName = goodsCarDO.getCarName();
            if(!finalName.startsWith(yearName)){
                finalName = yearName + "款 " + finalName;
            }

            GoodsCarDO finalCarDO = new GoodsCarDO();
            finalCarDO.setCarPower(powerName);
            finalCarDO.setCarYear(yearName);
            finalCarDO.setCarName(finalName);

            //数据存入map
            if(!modelMap.containsKey(modelId)){
                modelMap.put(modelId,modelName);
            }
            if(brandSeriesMap.containsKey(brandId)){
                brandSeriesMap.get(brandId).add(seriesId);
            }else{
                brandSeriesMap.put(brandId, Sets.newHashSet(seriesId));
            }

            if(seriesModelMap.containsKey(seriesId)){
                seriesModelMap.get(seriesId).add(modelId);
            }else{
                seriesModelMap.put(seriesId,Sets.newHashSet(modelId));
            }

            if(modelFinalCarDOMap.containsKey(modelId)){
                modelFinalCarDOMap.get(modelId).add(finalCarDO);
            }else{
                modelFinalCarDOMap.put(modelId,Sets.newHashSet(finalCarDO));
            }
        }
        //brand排序,brandId,firstWord从小到大排列
        List<CarCategoryDO> brandList = new ArrayList<>();
        for(Integer brandId : brandSeriesMap.keySet()){
            CarCategoryDO brandDO = carRedisManager.getCarByPrimaryId(brandId);

            if (null != brandDO) {
                brandList.add(brandDO);
            }
        }
        Collections.sort(brandList, new Comparator<CarCategoryDO>() {
            @Override
            public int compare(CarCategoryDO o1, CarCategoryDO o2) {
                return o1.getFirstWord().compareTo(o2.getFirstWord());
            }
        });

        //拼接需要的数据
        List<CarListSuit4GoodsBO> finalList = new ArrayList<>();

        for(CarCategoryDO brandDO : brandList){
            CarListSuit4GoodsBO suit4GoodsBO = new CarListSuit4GoodsBO();

            Integer brandId = brandDO.getId();
            String brandName = brandDO.getName();
            suit4GoodsBO.setName(brandName);
            suit4GoodsBO.setLogo(ShareConstantsUtil.getImgUrl(brandDO.getLogo()));
            //======车系
            List<CarListSuit4GoodsBO.Model> seriesBOList = new ArrayList<>();
            Integer seriseCount = 0;
            Set<Integer> seriesSet = brandSeriesMap.get(brandId);
            List<Integer> seriesList = new ArrayList(seriesSet);
            Collections.sort(seriesList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });
            for(Integer seriesId : seriesList){
                CarListSuit4GoodsBO.Model seriesBO = new CarListSuit4GoodsBO.Model();
                CarCategoryDO seriesDO = carRedisManager.getCarByPrimaryId(seriesId);
                if(null == seriesDO){
                    continue;
                }
                String seriesName = seriesDO.getName();
                String importInfo = seriesDO.getImportInfo();
                //ImportInfo为进口则进口，否则就company值
                if ("进口".equals(importInfo)) {
                    seriesBO.setCompany(importInfo);
                } else {
                    seriesBO.setCompany(seriesDO.getCompany());
                }
                seriesBO.setValue(seriesName);
                //======车型
                List<CarListSuit4GoodsBO.Displacement> modelBOList = new ArrayList<>();
                Set<Integer> modelSet = seriesModelMap.get(seriesId);
                List<Integer> modelList = new ArrayList(modelSet);
                //modelId 增序
                Collections.sort(modelList, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                });
                Integer modelCount = 0;
                for(Integer modelId : modelList){
                    CarListSuit4GoodsBO.Displacement modelBO = new CarListSuit4GoodsBO.Displacement();

                    String modelName = modelMap.get(modelId);
                    List<GoodsCarDO> carList = new ArrayList(modelFinalCarDOMap.get(modelId));
                    //power由小到大 year由大到小排序
                    Collections.sort(carList, new Comparator<GoodsCarDO>() {
                        @Override
                        public int compare(GoodsCarDO o1, GoodsCarDO o2) {
                            if (o2.getCarYear().equals(o1.getCarYear())) {
                                return o1.getCarPower().compareTo(o2.getCarPower());
                            }
                            return o2.getCarYear().compareTo(o1.getCarYear());
                        }
                    });
                    List<String> finalNameList = new ArrayList<>();
                    for(GoodsCarDO carDO : carList){
                        String finalName = carDO.getCarName();
                        if(Collections.frequency(finalNameList, finalName) < 1) finalNameList.add(finalName);
                    }
                    modelBO.setCount(1);
                    modelBO.setValue(modelName);
                    modelBO.setYearList(finalNameList);
                    modelCount += modelBO.getCount();
                    modelBOList.add(modelBO);

                }
                seriesBO.setDisplacements(modelBOList);
                seriesBO.setCount(modelCount);
                seriseCount += modelCount;
                seriesBOList.add(seriesBO);
            }
            suit4GoodsBO.setCount(seriseCount);
            suit4GoodsBO.setModels(seriesBOList);
            finalList.add(suit4GoodsBO);
        }

        return finalList;
    }


    /*==============================处理的private类===================================*/


    @Override
    public Result<List<CarCategoryDO>> findByLevel(Integer level){
        if(level==null || level<1){
            return Result.wrapErrorResult("", "请输入正确参数");
        }

        List<CarCategoryDO> list = carRedisManager.getByLevel(level);
        if(list.isEmpty()){
            return Result.wrapErrorResult("", "没有查到数据，请输入正确参数");
        }

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<CarCategoryDO>> findByPidList(List<Integer> pidList) {
        if(CollectionUtils.isEmpty(pidList)){
            return Result.wrapErrorResult("", "输入参数不能为空");
        }

        List<CarCategoryDO> list = carRedisManager.getCarListByPid(pidList);
        if(list.isEmpty()){
            return Result.wrapErrorResult("", "没有查到数据，请输入正确参数");
        }

        return Result.wrapSuccessfulResult(list);
    }

    /*********************************20160309 lyj 新增接口 start*******************************************************/
    @Override
    public Result<List<CarCategoryDO>> findByPrimaryIdList(List<Integer> ids) {
        if(CollectionUtils.isEmpty(ids) || ids.size() > ShareConstants.QUERY_BY_LIST_MAX_SIZE){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CarCategoryDO> list = carRedisManager.getByPrimaryIdList(ids);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<CarCategoryDO>> findByCarModels(List<Integer> otherModels, List<String> brandNames) {
        if(CollectionUtils.isEmpty(otherModels) || otherModels.size() > ShareConstants.QUERY_BY_LIST_MAX_SIZE){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CarCategoryDO> list = carRedisManager.getByCarModels(otherModels, brandNames);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<String>> findFirstWords(){
        List<String> list = carRedisManager.getFirstWords();
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<CarCategoryDO>> findByFirstWord(String firstWord){
        if(firstWord == null || "".equals(firstWord)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CarCategoryDO> list = carRedisManager.getByFirstWord(firstWord);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<CarCategoryDO>> findByLevelList(List<Integer> levels){
        if(CollectionUtils.isEmpty(levels) || levels.size() > 2){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CarCategoryDO> list = carRedisManager.getByLevelList(levels);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<CarCategoryDO>> findByFirstWordLevel(String firstWord, Integer level){
        if (firstWord == null || "".equals(firstWord) || level == null || level < 1) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CarCategoryDO> list = carRedisManager.getByFirstWordLevel(firstWord, level);
        if (list.isEmpty()) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return Result.wrapSuccessfulResult(list);
    }
    /*********************************20160309 lyj 新增接口 end*********************************************************/

}
