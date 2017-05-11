package com.tqmall.athena.redisBiz.center.goods;

import com.tqmall.athena.bean.bizBean.center.CenterGoodsCarDetailBO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarPictureDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarSubjoinDO;

import java.util.List;

/**
 * Created by lyj on 16/2/17.
 */
public interface CenterGoodsCarRedisManager {

    CenterGoodsCarDetailBO getGoodsCarDetailByGoodsCar(Integer goodsId, Integer carId);

    List<CenterGoodsCarDO> getGoodsCarByGoodsCar(Integer goodsId, Integer carId);

    CenterGoodsCarPictureDO getGoodsCarPictureById(Integer id);

    CenterGoodsCarSubjoinDO getGoodsCarSubjoinById(Integer id);

    CenterGoodsCarSubjoinDO getSubjoinByGoodsModel(Integer goodsId, Integer modelId);

    List<CenterGoodsCarPictureDO> getPictureByPicNum(String picNum);

    List<CenterGoodsCarDetailBO> getGcDetailByPicNumAndCar(String picNum, Integer carId);

    List<CenterGoodsCarDetailBO> getGcDetailByCatAndCar(Integer thirdCatId, Integer carId);
}
