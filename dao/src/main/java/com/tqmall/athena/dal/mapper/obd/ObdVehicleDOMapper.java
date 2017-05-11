package com.tqmall.athena.dal.mapper.obd;

import com.tqmall.athena.bean.entity.obd.ObdVehicleDO;
import com.tqmall.athena.dal.MyBatisRepository;

@MyBatisRepository
public interface ObdVehicleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ObdVehicleDO record);

    int insertSelective(ObdVehicleDO record);

    ObdVehicleDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ObdVehicleDO record);

    int updateByPrimaryKey(ObdVehicleDO record);
}