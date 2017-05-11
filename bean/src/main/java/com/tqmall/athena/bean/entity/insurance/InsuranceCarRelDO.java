package com.tqmall.athena.bean.entity.insurance;

import lombok.Data;

import java.util.Date;

@Data
public class InsuranceCarRelDO {
    private Integer id;

    private Date gmtCreate;

    private String vehicleCode;

    private Integer carId;

}