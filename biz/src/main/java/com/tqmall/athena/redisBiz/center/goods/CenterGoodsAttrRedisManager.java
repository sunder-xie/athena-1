package com.tqmall.athena.redisBiz.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsAttrDO;

import java.util.List;

/**
 * Created by zxg on 16/11/2.
 * 14:55
 * no bug,以后改代码的哥们，祝你好运~！！
 */
public interface CenterGoodsAttrRedisManager {

    List<CenterGoodsAttrDO> getGoodsAttrByGoodsId(Integer goodsId);
}
