package com.tqmall.athena.bean.entity.obd;

import lombok.Data;

import java.util.Date;

@Data
public class VehicleObdVehicleRelDO {
    private Integer id;

    private Date gmtCreate;

    private Integer vehicleId;

    private Integer obdVehicleId;

    private String vehicleCode;

}