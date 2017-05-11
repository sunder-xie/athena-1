package com.tqmall.athena.client.goods;

import com.tqmall.athena.domain.result.goods.GoodsCarDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/10/14.
 */
public interface GoodsService {
    /**
     * 根据商品id，查询适配车型（level 3级别）
     * @param goodsId 商品id
     * @return
     */
    Result<List<GoodsCarDTO>> suitableCarModels(Integer goodsId);

    /**
     * 根据商品id，车型id，查询适配的车款（level 6级别）
     * @param goodsId 商品id
     * @param carModelId 车型id，level 3级别
     * @return
     */
    Result<List<GoodsCarDTO>> suitableCars(Integer goodsId, Integer carModelId);

    /**
     * 根据商品id，查询适配车系（level 2级别）
     * @param goodsId 商品id
     * @return
     */
    Result<List<GoodsCarDTO>> suitableCarSeries(Integer goodsId);

    /**
     * 根据商品id，车系id，查询适配车型（level 3级别）
     * @param goodsId 商品id
     * @param carSeriesId 车系id，level 2级别
     * @return
     */
    Result<List<GoodsCarDTO>> suitableCarModelsBySeriesId(Integer goodsId, Integer carSeriesId);

    /**
     * 拷贝商品-车型关系
     * @param destGoodsId 需要添加关系数据的goodsId
     * @param srcGoodsId 关系数据来源goodsId
     * @return
     */
    Result<Boolean> copyGoodsCar(Integer destGoodsId, Integer srcGoodsId);

}
