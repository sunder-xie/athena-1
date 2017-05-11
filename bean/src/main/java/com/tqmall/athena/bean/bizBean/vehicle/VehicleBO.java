package com.tqmall.athena.bean.bizBean.vehicle;

import lombok.Data;

/**
 * Created by huangzhangting on 16/5/10.
 */
@Data
public class VehicleBO {
    private Integer id;

    private Integer pid;

    private String vehicleName;

    private Short grade;

    private Integer categoryId;

    private String categoryName;

    private String company;

    private String firstWord;

    private String logo;

    private Boolean isHot;

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

    private String carryingCapacity;

    private String rtu;

    private Boolean hasExtendAttr;

}
