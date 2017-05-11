package com.tqmall.athena.client.goods;

import com.tqmall.athena.domain.param.CategoryQueryPO;
import com.tqmall.athena.domain.result.goods.CategoryDTO;
import com.tqmall.athena.domain.result.goods.GoodsCateBrandDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 15/10/22.
 */
public interface GoodsCategoryService {
    /*
    *  根据类目id查找品牌
    * */
    Result<List<GoodsCateBrandDTO>> getCateBrands(Integer cateId);

    /**
     * 根据条件查询分类
     * @param queryPO
     * @return
     */
    Result<List<CategoryDTO>> getByCondition(CategoryQueryPO queryPO);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    Result<CategoryDTO> getCategoryById(Integer id);

    /**
     * 根据pid查询商用车分类
     * @param pid
     * @return
     */
    Result<List<CategoryDTO>> getCommercialCateByPid(Integer pid);
}
