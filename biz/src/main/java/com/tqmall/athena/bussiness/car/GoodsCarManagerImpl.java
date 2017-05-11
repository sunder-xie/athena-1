package com.tqmall.athena.bussiness.car;

import com.tqmall.athena.bean.bizBean.car.GoodsCarBO;
import com.tqmall.athena.bean.common.ShareConstantsUtil;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.car.GoodsCarDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.common.utils.PinyinUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.dal.mapper.car.GoodsCarDOMapper;
import com.tqmall.athena.redisBiz.car.CarRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by huangzhangting on 16/10/19.
 */
@Slf4j
@Service
public class GoodsCarManagerImpl implements GoodsCarManager{
    @Autowired
    private RedisClientTemplate redisClient;
    @Autowired
    private GoodsCarDOMapper goodsCarDOMapper;
    @Autowired
    private CarRedisManager carRedisManager;


    @Override
    public Result<List<GoodsCarBO>> suitableCarModels(Integer goodsId) {
        if (goodsId==null || goodsId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        String key = String.format(RedisKeyBean.CAR_MODELS_BY_GOODS_ID, goodsId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }
        if(redisStr != null){
            List<GoodsCarBO> list = JsonUtil.jsonStrToList(redisStr, GoodsCarBO.class);
            return ResultUtil.successResult(list);
        }

        List<CarCategoryDO> carModelList = goodsCarDOMapper.getCarModelByGoodsId(goodsId);
        if(carModelList.isEmpty()){
            redisClient.setNone(key);
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        List<CarCategoryDO> carBrandList = carRedisManager.getByLevel(1);

        List<GoodsCarBO> list = handleCarModels(carBrandList, carModelList);
        redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);

        return ResultUtil.successResult(list);
    }

    private List<GoodsCarBO> handleCarModels(List<CarCategoryDO> carBrandList, List<CarCategoryDO> carModelList){
        List<GoodsCarBO> list = new ArrayList<>();
        for(CarCategoryDO model : carModelList){
            GoodsCarBO goodsCarBO = new GoodsCarBO();
            goodsCarBO.setCarBrand(model.getBrand());
            goodsCarBO.setCarSeries(model.getSeries());
            goodsCarBO.setCompany(model.getCompany());
            goodsCarBO.setCarModel(model.getName());
            goodsCarBO.setCarModelId(model.getId());
            goodsCarBO.setImportInfo(model.getImportInfo());

            handleBrandInfo(model, carBrandList, goodsCarBO);

            list.add(goodsCarBO);
        }
        return list;
    }
    //处理品牌信息
    private void handleBrandInfo(CarCategoryDO car, List<CarCategoryDO> carBrandList, GoodsCarBO goodsCarBO){
        for(CarCategoryDO brand : carBrandList){
            if(car.getBrand().equals(brand.getName())){
                goodsCarBO.setBrandLogo(ShareConstantsUtil.getImgUrl(brand.getLogo()));
                goodsCarBO.setBrandFirstWord(brand.getFirstWord());
                break;
            }
        }
    }

    @Override
    public Result<List<GoodsCarBO>> suitableCars(Integer goodsId, Integer carModelId) {
        if(goodsId==null || goodsId<1 || carModelId==null || carModelId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        String key = String.format(RedisKeyBean.CARS_BY_GOODS_MODEL_ID, goodsId, carModelId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        if(redisStr != null){
            List<GoodsCarBO> list = JsonUtil.jsonStrToList(redisStr, GoodsCarBO.class);
            return ResultUtil.successResult(list);
        }

        List<GoodsCarDO> goodsCarDOList = goodsCarDOMapper.getCarByGoodsModelId(goodsId, carModelId);
        if(goodsCarDOList.isEmpty()){
            redisClient.setNone(key);
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        List<GoodsCarBO> list = handleCars(goodsCarDOList);
        redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);

        return ResultUtil.successResult(list);
    }

    private List<GoodsCarBO> handleCars(List<GoodsCarDO> goodsCarDOList){
        List<GoodsCarBO> list = new ArrayList<>();
        for(GoodsCarDO gc : goodsCarDOList){
            GoodsCarBO goodsCarBO = new GoodsCarBO();
            goodsCarBO.setCarId(gc.getCarId());
            String carName = gc.getCarYear()+"款 "+gc.getCarName();
            goodsCarBO.setCarName(carName);

            list.add(goodsCarBO);
        }
        return list;
    }

    @Override
    public Result<List<GoodsCarBO>> suitableCarSeries(Integer goodsId) {
        if (goodsId==null || goodsId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        String key = String.format(RedisKeyBean.CAR_SERIES_BY_GOODS_ID, goodsId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }
        if(redisStr != null){
            List<GoodsCarBO> list = JsonUtil.jsonStrToList(redisStr, GoodsCarBO.class);
            return ResultUtil.successResult(list);
        }

        List<CarCategoryDO> carSeriesList = goodsCarDOMapper.getCarSeriesByGoodsId(goodsId);
        if(carSeriesList.isEmpty()){
            redisClient.setNone(key);
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        List<GoodsCarBO> list = new ArrayList<>();
        for(CarCategoryDO car : carSeriesList){
            GoodsCarBO goodsCarBO = new GoodsCarBO();
            goodsCarBO.setCarBrand(car.getBrand());
            goodsCarBO.setCompany(car.getCompany());
            goodsCarBO.setCarSeries(car.getName());
            goodsCarBO.setCarSeriesId(car.getId());
            goodsCarBO.setImportInfo(car.getImportInfo());
            goodsCarBO.setBrandFirstWord(PinyinUtil.getFirstWord(car.getBrand()));

            list.add(goodsCarBO);
        }

        redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<GoodsCarBO>> suitableCarModelsBySeriesId(Integer goodsId, Integer carSeriesId) {
        if(goodsId==null || goodsId<1 || carSeriesId==null || carSeriesId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        String key = String.format(RedisKeyBean.CAR_MODELS_BY_GOODS_SERIES_ID, goodsId, carSeriesId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }
        if(redisStr != null){
            List<GoodsCarBO> list = JsonUtil.jsonStrToList(redisStr, GoodsCarBO.class);
            return ResultUtil.successResult(list);
        }

        List<GoodsCarDO> carModelList = goodsCarDOMapper.getCarModelByGoodsSeriesId(goodsId, carSeriesId);
        if(carModelList.isEmpty()){
            redisClient.setNone(key);
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        //因为查询出来的，有重复数据，在程序中处理掉
        Map<Integer, GoodsCarBO> carModelMap = new HashMap<>();
        for(GoodsCarDO model : carModelList){
            Integer carModelId = model.getCarModelId();
            GoodsCarBO goodsCarBO = carModelMap.get(carModelId);
            if(goodsCarBO==null){
                goodsCarBO = new GoodsCarBO();
                goodsCarBO.setCarModel(model.getCarModel());
                goodsCarBO.setCarModelId(carModelId);

                carModelMap.put(carModelId, goodsCarBO);
            }
        }

        List<GoodsCarBO> list = new ArrayList<>(carModelMap.values());

        redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);

        return ResultUtil.successResult(list);
    }


    @Override
    public boolean copyGoodsCar(Integer destGoodsId, Integer srcGoodsId) {
        log.info("to copy goods car, destGoodsId:{}, srcGoodsId:{}", destGoodsId, srcGoodsId);
        Assert.isTrue(srcGoodsId!=null && srcGoodsId>0, "非法参数, srcGoodsId="+srcGoodsId);
        Assert.isTrue(destGoodsId!=null && destGoodsId>0, "非法参数, destGoodsId="+destGoodsId);

        List<GoodsCarDO> srcList = carRedisManager.getGoodsCarListByGoodsId(srcGoodsId);
        if(CollectionUtils.isEmpty(srcList)){
            log.info("copy goods car, source list is empty, destGoodsId:{}, srcGoodsId:{}", destGoodsId, srcGoodsId);
            return true;
        }

        //修改已存在的无效数据
        Set<Integer> needModifyCarIds = grepSrcList(srcList, destGoodsId);
        if(!needModifyCarIds.isEmpty()){
            goodsCarDOMapper.updateInvalidDatas(destGoodsId, needModifyCarIds);
        }

        //批量插入数据
        if(!srcList.isEmpty()){
            Date now = new Date();
            for(GoodsCarDO srcDO : srcList){
                srcDO.setId(null);
                srcDO.setGoodsId(destGoodsId);
                srcDO.setGmtCreate(now);
                srcDO.setGmtModified(null);
            }
            batchInsert(srcList);
        }

        //清理相关缓存
        try {
            clearCache(destGoodsId);
        }catch (Exception e){
            log.error("to clear cache error, goodsId="+destGoodsId, e);
        }

        return true;
    }
    private Set<Integer> grepSrcList(List<GoodsCarDO> srcList, Integer destGoodsId){
        //有效的数据，排除已存在的有效数据
        Set<Integer> destHisCarIds = goodsCarDOMapper.getCarIdsByGoodsId(destGoodsId);
        if(!destHisCarIds.isEmpty()){
            int size = srcList.size();
            for(int i=0; i<size; i++){
                Integer carId = srcList.get(i).getCarId();
                if(destHisCarIds.contains(carId)){
                    srcList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        //无效的数据，修改已存在的无效数据
        Set<Integer> needModifyCarIds = new HashSet<>();
        Set<Integer> invalidDestHisCarIds = goodsCarDOMapper.getInvalidCarIdsByGoodsId(destGoodsId);
        if(!invalidDestHisCarIds.isEmpty()){
            int size = srcList.size();
            for(int i=0; i<size; i++){
                Integer carId = srcList.get(i).getCarId();
                if(invalidDestHisCarIds.contains(carId)){
                    needModifyCarIds.add(carId);
                    srcList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        return needModifyCarIds;
    }
    private void batchInsert(List<GoodsCarDO> list){
        //分批插入，每次插入500条
        int count = 500;
        int size = list.size();
        if(size <= count) {
            goodsCarDOMapper.batchInsert(list);
            return;
        }
        int n = size%count==0?size/count:size/count+1;
        for(int i=0; i<n; i++){
            int from = i * count;
            int to = from + count;
            if(to > size){
                to = size;
            }
            List<GoodsCarDO> subList = list.subList(from, to);
            goodsCarDOMapper.batchInsert(subList);
        }
    }
    private void clearCache(Integer goodsId){
        Set<String> keySet = new HashSet<>();
        keySet.add(String.format(RedisKeyBean.CAR_LIST_BY_GOODS_KEY, goodsId));
        keySet.add(String.format(RedisKeyBean.CAR_MODELS_BY_GOODS_ID, goodsId));
        keySet.add(String.format(RedisKeyBean.CAR_SERIES_BY_GOODS_ID, goodsId));

        String ks = String.format(RedisKeyBean.CARS_BY_GOODS_MODEL_ID, goodsId, -1);
        String pattern = ks.replace("-1", "*");
        keySet.addAll(redisClient.getKeys(pattern));

        ks = String.format(RedisKeyBean.CAR_MODELS_BY_GOODS_SERIES_ID, goodsId, -1);
        pattern = ks.replace("-1", "*");
        keySet.addAll(redisClient.getKeys(pattern));

        String[] keys = keySet.toArray(new String[keySet.size()]);
        redisClient.delKeys(keys);

        log.info("to clear cache, goodsId:{}, keySet:{}", goodsId, keySet);
    }

}
