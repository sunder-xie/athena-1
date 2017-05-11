package com.tqmall.athena.service.vehicle;

import com.tqmall.athena.bean.bizBean.vehicle.VehicleAttrValueBO;
import com.tqmall.athena.bean.bizBean.vehicle.VehicleBO;
import com.tqmall.athena.bussiness.vehicle.VehicleManager;
import com.tqmall.athena.client.vehicle.VehicleService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.vehicle.VehicleAttrValueDTO;
import com.tqmall.athena.domain.result.vehicle.VehicleDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/3/23.
 */
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleManager vehicleManager;


    @Override
    public Result<VehicleDTO> getVehicleById(Integer id) {
        Result<VehicleBO> result = vehicleManager.getVehicleBOById(id);
        return ResultUtil.handleResult(result, VehicleDTO.class);
    }

    @Override
    public Result<List<VehicleDTO>> getVehicleByPid(Integer pid) {
        Result<List<VehicleBO>> result = vehicleManager.getVehicleBOByPid(pid);
        return ResultUtil.handleResult4List(result, VehicleDTO.class);
    }

    @Override
    public Result<List<VehicleAttrValueDTO>> getExtAttrByVehicleId(Integer vehicleId) {
        Result<List<VehicleAttrValueBO>> result = vehicleManager.getExtAttrByVehicleId(vehicleId);
        return ResultUtil.handleResult4List(result, VehicleAttrValueDTO.class);
    }

}
