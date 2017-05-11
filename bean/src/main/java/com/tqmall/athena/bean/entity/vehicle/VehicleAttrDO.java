package com.tqmall.athena.bean.entity.vehicle;

import lombok.Data;

import java.util.Date;

@Data
public class VehicleAttrDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private String attrName;

}