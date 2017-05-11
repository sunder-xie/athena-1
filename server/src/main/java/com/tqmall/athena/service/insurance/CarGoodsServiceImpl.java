package com.tqmall.athena.service.insurance;

import com.tqmall.athena.bean.bizBean.insurance.RecommendOilBO;
import com.tqmall.athena.bussiness.insurance.CarGoodsManager;
import com.tqmall.athena.client.insurance.CarGoodsService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.insurance.RecommendOilDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/9/18.
 */
@Slf4j
public class CarGoodsServiceImpl implements CarGoodsService {
    @Autowired
    private CarGoodsManager carGoodsManager;


    @Override
    public Result<RecommendOilDTO> recommendEngineOil(String vin, String carYear, List<String> goodsSnList) {
        Result<RecommendOilBO> result = carGoodsManager.recommendEngineOil(vin, carYear, goodsSnList);
        return ResultUtil.handleResult(result, RecommendOilDTO.class);
    }

    @Override
    public Result<String> recommendOilFilter(String vin, String carYear) {
        return carGoodsManager.recommendOilFilter(vin, carYear);
    }

}
