package com.tqmall.athena.client.obd;

import com.tqmall.athena.domain.result.obd.ObdVehicleDTO;
import com.tqmall.core.common.entity.Result;

/**
 * Created by huangzhangting on 16/7/17.
 */
public interface ObdVehicleService {

    /**
     * 根据商用车车型id，查询obd供应商车型
     * @param vehicleId
     * @return
     */
    Result<ObdVehicleDTO> getObdVehicleForCommercial(Integer vehicleId);

    /**
     * 根据主键id，查询obd供应商车型
     * @param id
     * @return
     */
    Result<ObdVehicleDTO> getObdVehicleById(Integer id);
}
