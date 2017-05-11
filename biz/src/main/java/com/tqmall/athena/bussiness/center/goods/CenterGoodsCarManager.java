package com.tqmall.athena.bussiness.center.goods;

import com.tqmall.athena.bean.bizBean.center.CenterGoodsCarDetailBO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarSubjoinDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by lyj on 16/2/17.
 */
public interface CenterGoodsCarManager {
    /**
     *
     * @param goodsId 不能null
     * @param carId 不能null
     * @return
     */
    Result<CenterGoodsCarDetailBO> getGoodsCarDetailByGoodsCar(Integer goodsId, Integer carId);

    /**
     *
     * @param goodsId 可以null
     * @param carId 可以null
     * @return
     */
    Result<List<CenterGoodsCarDO>> getGoodsCarByGoodsCar(Integer goodsId, Integer carId);

    /**
     *
     * @param goodsId 不能null
     * @param modelId 不能null，车型id，level 3 级别
     * @return
     */
    Result<CenterGoodsCarSubjoinDO> getSubjoinByGoodsModel(Integer goodsId, Integer modelId);

    /**
     * 根据图编号、车款id，查询配件详情
     * @param picNum 不能空
     * @param carId 不能null level 6 级别id
     * @return
     */
    Result<List<CenterGoodsCarDetailBO>> getGcDetailByPicNumAndCar(String picNum, Integer carId);

    /**
     * 根据三级分类id和车款id，查询配件
     * @param thirdCatId 不能空,三级分类id
     * @param carId 不能null level 6 级别id
     * @return
     */
    Result<List<CenterGoodsCarDetailBO>> getGcDetailByCatAndCar(Integer thirdCatId, Integer carId);

}
