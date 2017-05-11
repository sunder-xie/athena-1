package com.tqmall.athena.bussiness.insurance;

import com.tqmall.athena.bean.bizBean.car.CarBO;
import com.tqmall.athena.bean.bizBean.insurance.RecommendOilBO;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bussiness.car.CarCategoryManager;
import com.tqmall.athena.bussiness.car.CarVinManager;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.external.beans.SearchGoodsBO;
import com.tqmall.athena.external.beans.SearchGoodsResult;
import com.tqmall.athena.external.http.GoodsSearchExt;
import com.tqmall.athena.external.insurance.PackageServiceExt;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * Created by huangzhangting on 16/9/19.
 */
@Slf4j
@Service
public class CarGoodsManagerImpl implements CarGoodsManager {
    private static final int DEFAULT_ROWS = 50; //默认分页行数

    @Value("${oil.cat.id}")
    private String oilCatId; //机油类目id
    @Value("${oil.filter.cat.id}")
    private String oilFilterCatId; //机油滤清器类目id
    @Value("${yun.xiu.brand.id}")
    private String yunXiuBrandId; //淘汽云修品牌id


    @Autowired
    private InsuranceCarRelManager insuranceCarRelManager;
    @Autowired
    private GoodsSearchExt goodsSearchExt;
    @Autowired
    private PackageServiceExt packageServiceExt;

    @Autowired
    private CarVinManager carVinManager;
    @Autowired
    private CarCategoryManager carCategoryManager;


    @Override
    public Result<String> recommendEngineOil(String vehicleCode, List<String> goodsSnList) {
        if(CollectionUtils.isEmpty(goodsSnList)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        Result<List<Integer>> carIdsResult = insuranceCarRelManager.getCarIds(vehicleCode);
        if(!carIdsResult.isSuccess()){
            return ResultUtil.errorResult(carIdsResult);
        }

        //优先推荐云修商品
        String goodsSn = handleGoodsSn(goodsSnList, carIdsResult.getData(), oilCatId, yunXiuBrandId);
        if(goodsSn == null){
            goodsSn = handleGoodsSn(goodsSnList, carIdsResult.getData(), oilCatId, null);
        }
        //如果查不到数据，接口使用方要求返回null
        return ResultUtil.successResult(goodsSn);
    }

    @Override
    public Result<String> recommendOilFilter(String vehicleCode) {
        Result<List<Integer>> carIdsResult = insuranceCarRelManager.getCarIds(vehicleCode);
        if(!carIdsResult.isSuccess()){
            return ResultUtil.errorResult(carIdsResult);
        }
        List<String> goodsSnList = packageServiceExt.queryPackageFilters();
        if(goodsSnList.isEmpty()){
            //如果查不到数据，接口使用方要求返回null
            return ResultUtil.successResult(null);
        }

        //优先推荐云修商品
        String goodsSn = handleGoodsSn(goodsSnList, carIdsResult.getData(), oilFilterCatId, yunXiuBrandId);
        if(goodsSn == null){
            goodsSn = handleGoodsSn(goodsSnList, carIdsResult.getData(), oilFilterCatId, null);
        }
        //如果查不到数据，接口使用方要求返回null
        return ResultUtil.successResult(goodsSn);
    }

    //处理商品编码
    private String handleGoodsSn(List<String> goodsSnList, List<Integer> carIdList, String catIds, String brandIds){
        for(Integer carId : carIdList){
            SearchGoodsResult goodsResult = goodsSearchExt.queryGoods(catIds, brandIds, carId, 1, DEFAULT_ROWS);
            if(goodsResult!=null && !CollectionUtils.isEmpty(goodsResult.getList())){
                String goodsSn = matchGoodsSn(goodsSnList, goodsResult.getList());
                if(goodsSn != null){
                    return goodsSn;
                }

                //查询分页数据
                Integer num = goodsResult.getNum();
                if(num != null && num > DEFAULT_ROWS){
                    int pages = (num%DEFAULT_ROWS)==0?num/DEFAULT_ROWS:(num/DEFAULT_ROWS+1);
                    while (pages > 1){
                        goodsResult = goodsSearchExt.queryGoods(catIds, brandIds, carId, pages, DEFAULT_ROWS);
                        goodsSn = handleGoodsSnForPage(goodsSnList, goodsResult);
                        if(goodsSn != null){
                            return goodsSn;
                        }
                        pages--;
                    }
                }
            }
        }

        return null;
    }

    //处理分页商品编码
    private String handleGoodsSnForPage(List<String> goodsSnList, SearchGoodsResult goodsResult){
        if(goodsResult!=null && !CollectionUtils.isEmpty(goodsResult.getList())){
            return matchGoodsSn(goodsSnList, goodsResult.getList());
        }
        return null;
    }

    //匹配商品编码
    private String matchGoodsSn(List<String> goodsSnList, List<SearchGoodsBO> goodsBOList){
        for(SearchGoodsBO goodsBO : goodsBOList){
            if(goodsSnList.contains(goodsBO.getGoodsSn())){
                return goodsBO.getGoodsSn();
            }
        }
        return null;
    }



    /** ========== 业务调整，代码重新写 ========== */

    @Override
    public Result<RecommendOilBO> recommendEngineOil(String vin, String carYear, List<String> goodsSnList) {
        if(CollectionUtils.isEmpty(goodsSnList)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        Result<List<CarBO>> carListResult = carVinManager.getCarBOByVin(vin);
        if(!carListResult.isSuccess()){
            log.info("recommendEngineOil, getCarBOByVin failed, vin:{} result:{}", vin, JsonUtil.objectToJsonStr(carListResult));
            return ResultUtil.errorResult(carListResult);
        }

        //获取合适的车款id
        List<Integer> carIdList = getSuitableCarIds(carListResult.getData(), carYear);

        //优先推荐云修商品
        Map<String, Object> map = handleGoodsSnNew(goodsSnList, carIdList, oilCatId, yunXiuBrandId);
        if(map == null){
            map = handleGoodsSnNew(goodsSnList, carIdList, oilCatId, null);
        }
        /** 匹配不上商品，则查询机油用量 */
        if(map == null){
            if(carIdList.size()==1){
                RecommendOilBO oilBO = new RecommendOilBO();
                //根据车款id查询机油用量
                handleOilCapacity(oilBO, carIdList.get(0));
                if(oilBO.getOilCapacity()==null){
                    log.info("recommendEngineOil failed, oil capacity is empty, vin:{} carYear:{} goodsSnList:{} carList:{}",
                            vin, carYear, goodsSnList, carListResult.getData().toString());
                    //如果查不到数据，接口使用方要求返回null
                    return ResultUtil.successResult(null);
                }else{
                    log.info("recommendEngineOil success, vin:{} carYear:{} suitableCarIds:{} result:{}", vin, carYear,
                            carIdList, oilBO.toString());
                    return ResultUtil.successResult(oilBO);
                }
            }
            log.info("recommendEngineOil failed, vin:{} carYear:{} goodsSnList:{} carList:{}",
                    vin, carYear, goodsSnList, carListResult.getData().toString());
            //多个车款不能确认推荐哪个，直接返回null
            return ResultUtil.successResult(null);
        }

        //log.info("recommendEngineOil, goodsSnMap:{}", map);

        RecommendOilBO oilBO = new RecommendOilBO();
        oilBO.setGoodsSn(map.get("goodsSn").toString());

        //根据车款id查询机油用量
        handleOilCapacity(oilBO, (Integer)map.get("carId"));

        log.info("recommendEngineOil success, vin:{} carYear:{} suitableCarIds:{} result:{}", vin, carYear,
                carIdList, oilBO.toString());

        return ResultUtil.successResult(oilBO);
    }
    //处理机油用量
    private void handleOilCapacity(RecommendOilBO oilBO, Integer carId){
        CarCategoryDO carCategoryDO = carCategoryManager.getCarCategoryByPrimaryId(carId);
        if(carCategoryDO != null) {
            BigDecimal oilCapacity = carCategoryDO.getOilCapacity();
            if (oilCapacity != null && oilCapacity.compareTo(BigDecimal.ZERO) > 0) {
                oilBO.setOilCapacity(oilCapacity);
            }
        }
    }

    @Override
    public Result<String> recommendOilFilter(String vin, String carYear) {
        Result<List<CarBO>> carListResult = carVinManager.getCarBOByVin(vin);
        if(!carListResult.isSuccess()){
            log.info("recommendOilFilter, getCarBOByVin failed, vin:{} result:{}", vin, JsonUtil.objectToJsonStr(carListResult));
            return ResultUtil.errorResult(carListResult);
        }

        List<String> goodsSnList = packageServiceExt.queryPackageFilters();
        if(goodsSnList.isEmpty()){
            //如果查不到数据，接口使用方要求返回null
            return ResultUtil.successResult(null, "获取insurance服务包的机滤列表失败");
        }

        //获取合适的车款id
        List<Integer> carIdList = getSuitableCarIds(carListResult.getData(), carYear);

        //优先推荐云修商品
        String goodsSn = handleGoodsSn(goodsSnList, carIdList, oilFilterCatId, yunXiuBrandId);
        if(goodsSn == null){
            goodsSn = handleGoodsSn(goodsSnList, carIdList, oilFilterCatId, null);
        }

        //打印日志
        if(goodsSn==null){
            log.info("recommendOilFilter failed, vin:{} carYear:{} goodsSnList:{} carList:{}", vin, carYear,
                    goodsSnList, carListResult.getData().toString());
        }else{
            log.info("recommendOilFilter success, vin:{} carYear:{} suitableCarIds:{} goodsSn:{}", vin, carYear,
                    carIdList, goodsSn);
        }

        //如果查不到数据，接口使用方要求返回null
        return ResultUtil.successResult(goodsSn);
    }

    /** 获取合适的车款id */
    private List<Integer> getSuitableCarIds(List<CarBO> carBOList, String carYear){
        List<Integer> carIdList = new ArrayList<>();
        if(carBOList.size()==1){
            carIdList.add(carBOList.get(0).getId());
            return carIdList;
        }

        if(carYear!=null){
            carYear = carYear.replace(" ", "");
            if(!"".equals(carYear)){
                for(CarBO car : carBOList){
                    if(carYear.contains(car.getYear())){
                        carIdList.add(car.getId());
                        return carIdList;
                    }
                }
            }
        }

        //根据年款升序，保证更早的车款优先被推荐
        Collections.sort(carBOList, new Comparator<CarBO>() {
            @Override
            public int compare(CarBO o1, CarBO o2) {
                return o1.getYear().compareTo(o2.getYear());
            }
        });
        for(CarBO carBO : carBOList){
            carIdList.add(carBO.getId());
        }

        return carIdList;
    }

    /**
     * 处理商品编码，新的方法
     * 返回结果包含：goodsSn 和 carId
     * */
    private Map<String, Object> handleGoodsSnNew(List<String> goodsSnList, List<Integer> carIdList, String catIds, String brandIds){
        for(Integer carId : carIdList){
            SearchGoodsResult goodsResult = goodsSearchExt.queryGoods(catIds, brandIds, carId, 1, DEFAULT_ROWS);
            if(goodsResult!=null && !CollectionUtils.isEmpty(goodsResult.getList())){
                String goodsSn = matchGoodsSn(goodsSnList, goodsResult.getList());
                if(goodsSn != null){
                    Map<String, Object> map = new HashMap<>();
                    map.put("goodsSn", goodsSn);
                    map.put("carId", carId);
                    return map;
                }

                //查询分页数据
                Integer num = goodsResult.getNum();
                if(num != null && num > DEFAULT_ROWS){
                    int pages = (num%DEFAULT_ROWS)==0?num/DEFAULT_ROWS:(num/DEFAULT_ROWS+1);
                    while (pages > 1){
                        goodsResult = goodsSearchExt.queryGoods(catIds, brandIds, carId, pages, DEFAULT_ROWS);
                        goodsSn = handleGoodsSnForPage(goodsSnList, goodsResult);
                        if(goodsSn != null){
                            Map<String, Object> map = new HashMap<>();
                            map.put("goodsSn", goodsSn);
                            map.put("carId", carId);
                            return map;
                        }
                        pages--;
                    }
                }
            }
        }

        return null;
    }

}
