package com.tqmall.athena.service.center.goods;

import com.tqmall.athena.bean.bizBean.center.CenterGoodsCarDetailBO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarSubjoinDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;
import com.tqmall.athena.bussiness.center.goods.CenterGoodsCarManager;
import com.tqmall.athena.bussiness.center.goods.CenterGoodsManager;
import com.tqmall.athena.client.center.goods.CenterGoodsService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarDetailDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarSubjoinDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/17.
 */
@Slf4j
public class CenterGoodsServiceImpl implements CenterGoodsService {
    @Autowired
    private CenterGoodsManager centerGoodsManager;

    @Autowired
    private CenterGoodsCarManager centerGoodsCarManager;


    @Override
    public Result<CenterGoodsDTO> getGoodsByPrimaryId(Integer goodsId) {
        Result<CenterGoodsDO> result = centerGoodsManager.getGoodsByPrimaryId(goodsId);
        return ResultUtil.handleResult(result, CenterGoodsDTO.class);
    }

    @Override
    public Result<CenterGoodsDTO> getGoodsByOeNumber(String oeNumber) {
        Result<CenterGoodsDO> result = centerGoodsManager.getGoodsByOeNumber(oeNumber);
        return ResultUtil.handleResult(result, CenterGoodsDTO.class);
    }

    @Override
    public Result<List<CenterGoodsDTO>> getGoodsByThirdCatId(Integer thirdCatId) {
        Result<List<CenterGoodsDO>> result = centerGoodsManager.getGoodsByThirdCatId(thirdCatId);
        return ResultUtil.handleResult4List(result, CenterGoodsDTO.class);
    }

    @Override
    public Result<CenterGoodsCarDetailDTO> getGoodsCarDetailByGoodsCar(Integer goodsId, Integer carId) {
        Result<CenterGoodsCarDetailBO> result = centerGoodsCarManager.getGoodsCarDetailByGoodsCar(goodsId, carId);
        return ResultUtil.handleResult(result, CenterGoodsCarDetailDTO.class);
    }

    @Override
    public Result<List<CenterGoodsCarDTO>> getGoodsCarByGoodsCar(Integer goodsId, Integer carId) {
        Result<List<CenterGoodsCarDO>> result = centerGoodsCarManager.getGoodsCarByGoodsCar(goodsId, carId);
        return ResultUtil.handleResult4List(result, CenterGoodsCarDTO.class);
    }

    @Override
    public Result<CenterGoodsCarSubjoinDTO> getSubjoinByGoodsModel(Integer goodsId, Integer modelId) {
        Result<CenterGoodsCarSubjoinDO> result = centerGoodsCarManager.getSubjoinByGoodsModel(goodsId, modelId);
        return ResultUtil.handleResult(result, CenterGoodsCarSubjoinDTO.class);
    }

    @Override
    public Result<List<CenterGoodsDTO>> getGoodsByPicNumAndCar(String picNum, Integer carId) {
        Result<List<CenterGoodsDO>> result = centerGoodsManager.getGoodsByPicNumAndCar(picNum, carId);
        return ResultUtil.handleResult4List(result, CenterGoodsDTO.class);
    }

    @Override
    public Result<List<CenterGoodsCarDetailDTO>> getGcDetailByPicNumAndCar(String picNum, Integer carId) {
        Result<List<CenterGoodsCarDetailBO>> result =
                centerGoodsCarManager.getGcDetailByPicNumAndCar(picNum, carId);
        return ResultUtil.handleResult4List(result, CenterGoodsCarDetailDTO.class);
    }

    @Override
    public Result<List<CenterGoodsCarDetailDTO>> getGcDetailByCatAndCar(Integer thirdCatId, Integer carId) {
        Result<List<CenterGoodsCarDetailBO>> result =
                centerGoodsCarManager.getGcDetailByCatAndCar(thirdCatId, carId);
        return ResultUtil.handleResult4List(result, CenterGoodsCarDetailDTO.class);
    }

}
