package com.tqmall.athena.dal.mapper.vehicle;

import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface VehicleCategoryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleCategoryDO record);

    int insertSelective(VehicleCategoryDO record);

    VehicleCategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleCategoryDO record);

    int updateByPrimaryKey(VehicleCategoryDO record);

    List<VehicleCategoryDO> selectByPid(Integer pid);
}