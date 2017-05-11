package com.tqmall.athena.domain.result.vehicle;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/5/10.
 */
@Data
public class VehicleDTO implements Serializable{
    private Integer id;

    private Integer pid;  //父级id

    private String vehicleName;  //名称

    private Short grade;  //级别，1：品牌、2：车系、3：车型

    private String categoryName;  //车辆分类

    private String company;  //厂商

    private String firstWord;  //首字母

    private String logo;  //图片

    private Boolean isHot;  //true：热门，false：非热门

    private String noticeNumber;  //公告型号

    private String engineNumber;  //发动机型号

    private String chassisNumber;  //底盘型号

    private String maxHorsePower;  //最大马力

    private String drivingMode;  //驱动方式

    private String fuelType;  //燃料类型

    private String brand;  //品牌

    private String series;  //车系

    private String totalWeight;  //总质量(kg)

    private String curbWeight;  //整备质量(kg)

    private String carryingCapacity;  //载重(kg)

    private String rtu;  //车型分类代号

    private Boolean hasExtendAttr;  //true：有扩展属性，false：没有扩展属性

}
