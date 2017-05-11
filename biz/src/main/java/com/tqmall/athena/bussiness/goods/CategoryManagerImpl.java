package com.tqmall.athena.bussiness.goods;

import com.tqmall.athena.bean.bizBean.params.CategoryQueryParam;
import com.tqmall.athena.bean.entity.goods.CategoryBrandDO;
import com.tqmall.athena.bean.entity.goods.GoodsCategoryDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.goods.CategoryRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangzhangting on 15/10/22.
 */
@Service
@Slf4j
public class CategoryManagerImpl implements CategoryManager {
    @Autowired
    private CategoryRedisManager categoryRedisManager;

    @Override
    public Result<List<CategoryBrandDO>> findCateBrand(Integer cateId){
        if(cateId==null || cateId<=0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<CategoryBrandDO> list = categoryRedisManager.getCateBrand(cateId);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<GoodsCategoryDO>> findByCondition(CategoryQueryParam param) {
        if(param==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<GoodsCategoryDO> list = categoryRedisManager.getByCondition(param);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(list);
    }
}
