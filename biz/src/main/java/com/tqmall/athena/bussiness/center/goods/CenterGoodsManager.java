package com.tqmall.athena.bussiness.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/17.
 */
public interface CenterGoodsManager {

    Result<CenterGoodsDO> getGoodsByPrimaryId(Integer goodsId);

    Result<CenterGoodsDO> getGoodsByOeNumber(String oeNumber);

    Result<List<CenterGoodsDO>> getGoodsByThirdCatId(Integer thirdCatId);

    Result<List<CenterGoodsDO>> getGoodsByPicNumAndCar(String picNum, Integer carId);
}
