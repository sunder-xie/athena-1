package com.tqmall.athena.dal.mapper.vehicle;

import com.tqmall.athena.bean.entity.vehicle.VehicleAttrValueDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface VehicleAttrValueDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleAttrValueDO record);

    int insertSelective(VehicleAttrValueDO record);

    VehicleAttrValueDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleAttrValueDO record);

    int updateByPrimaryKey(VehicleAttrValueDO record);

    List<VehicleAttrValueDO> selectByVehicleId(Integer vehicleId);

    List<VehicleAttrValueDO> selectByVehicleIdList(@Param("vehicleIdList")List<Integer> vehicleIdList);
}