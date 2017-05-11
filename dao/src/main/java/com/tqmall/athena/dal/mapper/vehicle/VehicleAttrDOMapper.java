package com.tqmall.athena.dal.mapper.vehicle;

import com.tqmall.athena.bean.entity.vehicle.VehicleAttrDO;
import com.tqmall.athena.dal.MyBatisRepository;

@MyBatisRepository
public interface VehicleAttrDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleAttrDO record);

    int insertSelective(VehicleAttrDO record);

    VehicleAttrDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleAttrDO record);

    int updateByPrimaryKey(VehicleAttrDO record);
}