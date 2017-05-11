package com.tqmall.athena.bussiness.obd;

import com.tqmall.athena.bean.entity.obd.ObdVehicleDO;
import com.tqmall.core.common.entity.Result;

/**
 * Created by huangzhangting on 16/7/17.
 */
public interface ObdVehicleManager {

    Result<ObdVehicleDO> getObdVehicleByVehicleIdCode(Integer vehicleId, String vehicleCode);

    Result<ObdVehicleDO> getObdVehicleById(Integer id);
}
