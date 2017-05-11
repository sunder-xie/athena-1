package com.tqmall.athena.client.center.goods;

import com.tqmall.athena.domain.result.center.goods.CenterGoodsAttrDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 16/11/2.
 * 14:41
 * no bug,以后改代码的哥们，祝你好运~！！
 */
public interface CenterGoodsAttrService {

    Result<List<CenterGoodsAttrDTO>> getGoodsAttrByGoodsId(Integer goodsId);
}
