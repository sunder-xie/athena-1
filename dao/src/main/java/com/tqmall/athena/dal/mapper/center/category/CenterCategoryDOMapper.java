package com.tqmall.athena.dal.mapper.center.category;

import com.tqmall.athena.bean.entity.center.category.CenterCategoryDO;
import com.tqmall.athena.dal.MyBatisRepository;

@MyBatisRepository
public interface CenterCategoryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CenterCategoryDO record);

    int insertSelective(CenterCategoryDO record);

    CenterCategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CenterCategoryDO record);

    int updateByPrimaryKey(CenterCategoryDO record);
}