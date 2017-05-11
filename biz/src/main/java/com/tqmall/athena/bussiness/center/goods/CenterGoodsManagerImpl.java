package com.tqmall.athena.bussiness.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.center.goods.CenterGoodsRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/17.
 */
@Slf4j
@Service
public class CenterGoodsManagerImpl implements CenterGoodsManager {
    @Autowired
    private CenterGoodsRedisManager centerGoodsRedisManager;

    @Override
    public Result<CenterGoodsDO> getGoodsByPrimaryId(Integer goodsId) {
        if(goodsId==null || goodsId<1)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        CenterGoodsDO goodsDO = centerGoodsRedisManager.getGoodsByPrimaryId(goodsId);
        if(goodsDO==null)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(goodsDO);
    }

    @Override
    public Result<CenterGoodsDO> getGoodsByOeNumber(String oeNumber) {
        if(StringUtils.isEmpty(oeNumber))
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        CenterGoodsDO goodsDO = centerGoodsRedisManager.getGoodsByOeNumber(oeNumber);
        if(goodsDO==null)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(goodsDO);
    }

    @Override
    public Result<List<CenterGoodsDO>> getGoodsByThirdCatId(Integer thirdCatId) {
        if(thirdCatId ==null || thirdCatId<1)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        List<CenterGoodsDO> list = centerGoodsRedisManager.getGoodsByThirdCatId(thirdCatId);
        if(list.isEmpty())
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<CenterGoodsDO>> getGoodsByPicNumAndCar(String picNum, Integer carId) {
        if(StringUtils.isEmpty(picNum) || carId==null || carId<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<CenterGoodsDO> list = centerGoodsRedisManager.getGoodsByPicNumAndCar(picNum, carId);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(list);
    }
}
