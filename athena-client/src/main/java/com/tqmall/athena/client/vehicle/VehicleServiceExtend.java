package com.tqmall.athena.client.vehicle;

import com.tqmall.athena.domain.result.vehicle.VehicleAttrValueDTO;
import com.tqmall.athena.domain.result.vehicle.VehicleDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/6/17.
 */
public interface VehicleServiceExtend {
    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    Result<VehicleDTO> getVehicleById(Integer id);

    /**
     * 根据父级id查询，子级集合
     * @param pid
     * @return
     */
    Result<List<VehicleDTO>> getVehicleByPid(Integer pid);

    /**
     * 根据车型id查询扩展属性
     * @param vehicleId
     * @return
     */
    Result<List<VehicleAttrValueDTO>> getExtAttrByVehicleId(Integer vehicleId);

    /**
     * 根据公告号，查询车型
     * @param noticeNum
     * @return
     */
    Result<List<VehicleDTO>> getVehicleByNoticeNum(String noticeNum);
}
