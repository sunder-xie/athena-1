package com.tqmall.athena.bussiness.center.goods;

import com.tqmall.athena.bean.bizBean.center.CenterGoodsCarDetailBO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarSubjoinDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.center.goods.CenterGoodsCarRedisManager;
import com.tqmall.athena.redisBiz.center.goods.CenterGoodsRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyj on 16/2/17.
 */
@Slf4j
@Service
public class CenterGoodsCarManagerImpl implements CenterGoodsCarManager {
    @Autowired
    private CenterGoodsRedisManager centerGoodsRedisManager;
    @Autowired
    private CenterGoodsCarRedisManager centerGoodsCarRedisManager;

    @Override
    public Result<CenterGoodsCarDetailBO> getGoodsCarDetailByGoodsCar(Integer goodsId, Integer carId) {
        if(goodsId==null || carId == null || goodsId<1 || carId<1) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        CenterGoodsCarDetailBO detailBO = centerGoodsCarRedisManager.getGoodsCarDetailByGoodsCar(goodsId, carId);
        if(detailBO==null) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(detailBO);
    }

    @Override
    public Result<List<CenterGoodsCarDO>> getGoodsCarByGoodsCar(Integer goodsId, Integer carId) {
        if(goodsId==null && carId==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        if(goodsId!=null && goodsId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        if(carId!=null && carId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CenterGoodsCarDO> list = centerGoodsCarRedisManager.getGoodsCarByGoodsCar(goodsId, carId);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(list);
    }

    @Override
    public Result<CenterGoodsCarSubjoinDO> getSubjoinByGoodsModel(Integer goodsId, Integer modelId) {
        if(goodsId==null || goodsId<1 || modelId==null || modelId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        CenterGoodsCarSubjoinDO subjoinDO = centerGoodsCarRedisManager.getSubjoinByGoodsModel(goodsId, modelId);
        if(subjoinDO==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(subjoinDO);
    }

    @Override
    public Result<List<CenterGoodsCarDetailBO>> getGcDetailByPicNumAndCar(String picNum, Integer carId) {
        if(StringUtils.isEmpty(picNum) || carId==null || carId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<CenterGoodsCarDetailBO> list = centerGoodsCarRedisManager.getGcDetailByPicNumAndCar(picNum, carId);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<CenterGoodsCarDetailBO>> getGcDetailByCatAndCar(Integer thirdCatId, Integer carId) {
        if(thirdCatId == null || thirdCatId < 1 || carId==null || carId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<CenterGoodsCarDetailBO> list = centerGoodsCarRedisManager.getGcDetailByCatAndCar(thirdCatId, carId);
//        if(list.isEmpty()){
//            return ResultUtil.errorResult(DataError.ARG_ERROR);
//        }
        return ResultUtil.successResult(list);
    }
}
