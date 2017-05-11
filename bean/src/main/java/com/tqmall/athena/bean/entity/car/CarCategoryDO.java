package com.tqmall.athena.bean.entity.car;

import com.tqmall.athena.bean.common.ShareConstantsUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * created by 钟熙耿 on 2015-09-22
 * 线上车型库
 */

@Data
public class CarCategoryDO implements Serializable {
    private Integer id;

    private String name;

    private String power;

    private Integer level;

    private String firstWord;

    private Integer sort;

    private Integer pid;

    private Integer isDel;

    private String model;

    private String series;

    private String brand;

    private String company;

    private Byte country;

    private String carType;

    private String carLevel;

    private String importInfo;

    private String logo;

    private Integer flag;

    private Integer isHot;

    private String year;

    private String engineType;

    private String chassisNumber;

    private String guidePrice;

    private BigDecimal oilCapacity; //机油用量
}