package com.tqmall.athena.dal.mapper.vehicle;

import com.tqmall.athena.bean.entity.vehicle.VehicleDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface VehicleDOMapper {

    VehicleDO selectByPrimaryKey(Integer id);

    List<VehicleDO> selectByPid(Integer pid);

    List<VehicleDO> selectByNoticeNum(String noticeNum);
}