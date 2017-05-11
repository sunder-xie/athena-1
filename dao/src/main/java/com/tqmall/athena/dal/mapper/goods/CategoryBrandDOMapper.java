package com.tqmall.athena.dal.mapper.goods;

import com.tqmall.athena.bean.entity.goods.CategoryBrandDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CategoryBrandDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryBrandDO record);

    int insertSelective(CategoryBrandDO record);

    CategoryBrandDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryBrandDO record);

    int updateByPrimaryKey(CategoryBrandDO record);

    List<CategoryBrandDO> selectCateBrand(Integer cateId);
}