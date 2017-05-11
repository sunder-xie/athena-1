package com.tqmall.athena.dal.mapper.obd;

import com.tqmall.athena.bean.entity.obd.VehicleObdVehicleRelDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface VehicleObdVehicleRelDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleObdVehicleRelDO record);

    int insertSelective(VehicleObdVehicleRelDO record);

    VehicleObdVehicleRelDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleObdVehicleRelDO record);

    int updateByPrimaryKey(VehicleObdVehicleRelDO record);

    VehicleObdVehicleRelDO selectByParam(@Param("vehicleId")Integer vehicleId,
                                         @Param("vehicleCode")String vehicleCode);
}