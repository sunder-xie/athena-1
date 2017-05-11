package com.tqmall.athena.bussiness.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsAttrDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 16/11/2.
 * 14:53
 * no bug,以后改代码的哥们，祝你好运~！！
 */
public interface CenterGoodsAttrManager {
    //根据goodsId 获得 其属性的列表
    Result<List<CenterGoodsAttrDO>> getGoodsAttrByGoodsId(Integer goodsId);
}
