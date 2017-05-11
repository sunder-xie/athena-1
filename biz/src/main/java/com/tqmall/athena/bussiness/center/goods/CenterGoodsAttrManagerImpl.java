package com.tqmall.athena.bussiness.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsAttrDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.center.goods.CenterGoodsAttrRedisManager;
import com.tqmall.core.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxg on 16/11/2.
 * 14:54
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Service
public class CenterGoodsAttrManagerImpl implements CenterGoodsAttrManager {
    @Autowired
    private CenterGoodsAttrRedisManager attrRedisManager;
    @Override
    public Result<List<CenterGoodsAttrDO>> getGoodsAttrByGoodsId(Integer goodsId) {
        if(goodsId == null || goodsId < 1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<CenterGoodsAttrDO> attrDOList = attrRedisManager.getGoodsAttrByGoodsId(goodsId);
        return Result.wrapSuccessfulResult(attrDOList);
    }
}
