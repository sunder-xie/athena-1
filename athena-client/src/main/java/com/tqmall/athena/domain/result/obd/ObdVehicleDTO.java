package com.tqmall.athena.domain.result.obd;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/7/17.
 */
@Data
public class ObdVehicleDTO implements Serializable{
    private Integer id;

    private Integer pid;

    private String vehicleName;

    private Integer vehicleGrade;
}
