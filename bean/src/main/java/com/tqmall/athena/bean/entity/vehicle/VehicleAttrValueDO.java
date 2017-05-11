package com.tqmall.athena.bean.entity.vehicle;

import lombok.Data;

import java.util.Date;

@Data
public class VehicleAttrValueDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private String attrName;

    private Integer attrId;

    private String attrValue;

    private Integer vehicleId;

}