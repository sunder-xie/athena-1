package com.tqmall.athena.dal.mapper.goods;

import com.tqmall.athena.bean.bizBean.params.CategoryQueryParam;
import com.tqmall.athena.bean.entity.goods.GoodsCategoryDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface GoodsCategoryDOMapper {

    GoodsCategoryDO selectByPrimaryKey(Short catId);

    List<GoodsCategoryDO> selectByCondition(CategoryQueryParam param);
}