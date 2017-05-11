package com.tqmall.athena.dal.mapper.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarCategoryDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterCarCategoryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CenterCarCategoryDO record);

    int insertSelective(CenterCarCategoryDO record);

    CenterCarCategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CenterCarCategoryDO record);

    /*==========自定义============*/
    List<CenterCarCategoryDO> selectListByDO(CenterCarCategoryDO record);
}