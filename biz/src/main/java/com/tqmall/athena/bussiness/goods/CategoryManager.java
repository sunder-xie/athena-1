package com.tqmall.athena.bussiness.goods;

import com.tqmall.athena.bean.bizBean.params.CategoryQueryParam;
import com.tqmall.athena.bean.entity.goods.CategoryBrandDO;
import com.tqmall.athena.bean.entity.goods.GoodsCategoryDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 15/10/22.
 */
public interface CategoryManager {
    /**
     * 根据类目id，查找品牌
     * @param cateId
     * @return
     */
    Result<List<CategoryBrandDO>> findCateBrand(Integer cateId);

    /**
     * 根据条件查询商品类目
     * @param param
     * @return
     */
    Result<List<GoodsCategoryDO>> findByCondition(CategoryQueryParam param);
}
