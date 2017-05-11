package com.tqmall.athena.bean.entity.vehicle;

import com.tqmall.athena.bean.common.ShareConstantsUtil;
import lombok.Data;

import java.util.Date;

@Data
public class VehicleDO {

    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private Integer pid;

    private String vehicleName;

    private Short grade;

    private Integer categoryId;

    private String categoryName;

    private String company;

    private String firstWord;

    private String logo;

    private Boolean isHot;

    private Boolean hasExtendAttr;

    private String noticeNumber;

    private String engineNumber;

    private String chassisNumber;

    private String maxHorsePower;

    private String drivingMode;

    private String fuelType;

    private String brand;

    private String series;

    private String totalWeight;

    private String curbWeight;

    private String rtu;

    private String carryingCapacity;

    public Boolean getHasExtendAttr() {
        if(hasExtendAttr==null)
            return false;
        return hasExtendAttr;
    }

    public String getLogo() {
        return ShareConstantsUtil.getImgUrl(logo);
    }
}