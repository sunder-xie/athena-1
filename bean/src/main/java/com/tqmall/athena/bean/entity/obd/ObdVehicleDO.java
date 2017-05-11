package com.tqmall.athena.bean.entity.obd;

import lombok.Data;

import java.util.Date;

@Data
public class ObdVehicleDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private Integer pid;

    private String vehicleName;

    private Integer vehicleGrade;

}