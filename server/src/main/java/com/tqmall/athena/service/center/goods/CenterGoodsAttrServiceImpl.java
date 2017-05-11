package com.tqmall.athena.service.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsAttrDO;
import com.tqmall.athena.bussiness.center.goods.CenterGoodsAttrManager;
import com.tqmall.athena.client.center.goods.CenterGoodsAttrService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsAttrDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zxg on 16/11/2.
 * 14:46
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Slf4j
public class CenterGoodsAttrServiceImpl implements CenterGoodsAttrService {
    @Autowired
    private CenterGoodsAttrManager centerGoodsAttrManager;

    @Override
    public Result<List<CenterGoodsAttrDTO>> getGoodsAttrByGoodsId(Integer goodsId) {
        Result<List<CenterGoodsAttrDO>> result = centerGoodsAttrManager.getGoodsAttrByGoodsId(goodsId);
        return ResultUtil.handleResult4List(result, CenterGoodsAttrDTO.class);
    }
}
