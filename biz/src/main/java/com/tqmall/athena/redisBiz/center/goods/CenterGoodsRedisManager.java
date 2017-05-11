package com.tqmall.athena.redisBiz.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/17.
 */
public interface CenterGoodsRedisManager {

    CenterGoodsDO getGoodsByPrimaryId(Integer goodsId);

    CenterGoodsDO getGoodsByOeNumber(String oeNumber);

    List<CenterGoodsDO> getGoodsByThirdCatId(Integer thirdCatId);

    List<CenterGoodsDO> getGoodsByPicNumAndCar(String picNum, Integer carId);

}
