package com.tqmall.athena.dal.mapper.center.category;

import com.tqmall.athena.bean.entity.center.category.CenterCategoryShowDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterCategoryShowDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CenterCategoryShowDO record);

    CenterCategoryShowDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CenterCategoryShowDO record);


    List<CenterCategoryShowDO> selectByShowDO(CenterCategoryShowDO record);
}