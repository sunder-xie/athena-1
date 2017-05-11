package com.tqmall.athena.service.obd;

import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.obd.ObdVehicleDO;
import com.tqmall.athena.bussiness.obd.ObdVehicleManager;
import com.tqmall.athena.client.obd.ObdVehicleService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.obd.ObdVehicleDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangzhangting on 16/7/17.
 */
@Slf4j
public class ObdVehicleServiceImpl implements ObdVehicleService {
    @Autowired
    private ObdVehicleManager obdVehicleManager;

    @Override
    public Result<ObdVehicleDTO> getObdVehicleForCommercial(Integer vehicleId) {
        Result<ObdVehicleDO> result =
                obdVehicleManager.getObdVehicleByVehicleIdCode(vehicleId, ShareConstants.COMMERCIAL_CAR);
        return ResultUtil.handleResult(result, ObdVehicleDTO.class);
    }

    @Override
    public Result<ObdVehicleDTO> getObdVehicleById(Integer id) {
        Result<ObdVehicleDO> result = obdVehicleManager.getObdVehicleById(id);
        return ResultUtil.handleResult(result, ObdVehicleDTO.class);
    }
}
