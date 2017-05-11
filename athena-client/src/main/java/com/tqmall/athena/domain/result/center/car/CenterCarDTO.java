package com.tqmall.athena.domain.result.center.car;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/2/2.
 */
@Data
public class CenterCarDTO implements Serializable {
    private Integer id;

    private String name;

    private String power;

    private Byte level;

    private String firstWord;

    private Integer sort;

    private Integer pid;

    private String model;

    private String series;

    private String brand;

    private String company;

    private Byte country;

    private String carType;

    private String carLevel;

    private String importInfo;

    private String logo;

    private Boolean isHot;

    private String year;

    private String engineType;

    private String chassisNumber;

    private String guidePrice;

    private Integer modelId;

}
