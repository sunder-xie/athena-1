package com.tqmall.athena.client.center.goods;

import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarDetailDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarSubjoinDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/17.
 */
public interface CenterGoodsService {

    Result<CenterGoodsDTO> getGoodsByPrimaryId(Integer goodsId);

    Result<CenterGoodsDTO> getGoodsByOeNumber(String oeNumber);

    Result<List<CenterGoodsDTO>> getGoodsByThirdCatId(Integer thirdCatId);

    /**
     * 根据商品id，车款id，查车型-商品详细信息
     * @param goodsId 不能null
     * @param carId 不能null，车款id，level 6 级别
     * @return
     */
    Result<CenterGoodsCarDetailDTO> getGoodsCarDetailByGoodsCar(Integer goodsId, Integer carId);

    /**
     * 根据商品id，车款id，查车型-商品对应关系
     * @param goodsId 可以null
     * @param carId 可以null，车款id，level 6 级别
     * @return
     */
    Result<List<CenterGoodsCarDTO>> getGoodsCarByGoodsCar(Integer goodsId, Integer carId);

    /**
     * 根据商品id，车型id，查找车型-商品额外信息，例如备注、用量等
     * @param goodsId 不能null
     * @param modelId 不能null，车型id，level 3 级别
     * @return
     */
    Result<CenterGoodsCarSubjoinDTO> getSubjoinByGoodsModel(Integer goodsId, Integer modelId);

    /**
     * 根据图编号、车款id，查询配件
     * @param picNum 不能空
     * @param carId 不能null level 6 级别id
     * @return
     */
    Result<List<CenterGoodsDTO>> getGoodsByPicNumAndCar(String picNum, Integer carId);

    /**
     * 根据图编号、车款id，查询配件详情
     * @param picNum 不能空
     * @param carId 不能null level 6 级别id
     * @return
     */
    Result<List<CenterGoodsCarDetailDTO>> getGcDetailByPicNumAndCar(String picNum, Integer carId);


    /**
     * 根据三级分类和carId 获得 配件数据和备注等信息
     * @param thirdCatId 三级分类，不能为null
     * @param carId level 6 级别id，不能为null
     * @return
     */
    Result<List<CenterGoodsCarDetailDTO>> getGcDetailByCatAndCar(Integer thirdCatId, Integer carId);


}
