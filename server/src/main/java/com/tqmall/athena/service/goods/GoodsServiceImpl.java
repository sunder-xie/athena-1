package com.tqmall.athena.service.goods;

import com.tqmall.athena.bean.bizBean.car.GoodsCarBO;
import com.tqmall.athena.bussiness.car.GoodsCarManager;
import com.tqmall.athena.client.goods.GoodsService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.goods.GoodsCarDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/10/19.
 */
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsCarManager goodsCarManager;

    @Override
    public Result<List<GoodsCarDTO>> suitableCarModels(Integer goodsId) {
        Result<List<GoodsCarBO>> result = goodsCarManager.suitableCarModels(goodsId);
        return ResultUtil.handleResult4List(result, GoodsCarDTO.class);
    }

    @Override
    public Result<List<GoodsCarDTO>> suitableCars(Integer goodsId, Integer carModelId) {
        Result<List<GoodsCarBO>> result = goodsCarManager.suitableCars(goodsId, carModelId);
        return ResultUtil.handleResult4List(result, GoodsCarDTO.class);
    }

    @Override
    public Result<List<GoodsCarDTO>> suitableCarSeries(Integer goodsId) {
        Result<List<GoodsCarBO>> result = goodsCarManager.suitableCarSeries(goodsId);
        return ResultUtil.handleResult4List(result, GoodsCarDTO.class);
    }

    @Override
    public Result<List<GoodsCarDTO>> suitableCarModelsBySeriesId(Integer goodsId, Integer carSeriesId) {
        Result<List<GoodsCarBO>> result = goodsCarManager.suitableCarModelsBySeriesId(goodsId, carSeriesId);
        return ResultUtil.handleResult4List(result, GoodsCarDTO.class);
    }

    @Override
    public Result<Boolean> copyGoodsCar(Integer destGoodsId, Integer srcGoodsId) {
        return ResultUtil.successResult(goodsCarManager.copyGoodsCar(destGoodsId, srcGoodsId));
    }
}
