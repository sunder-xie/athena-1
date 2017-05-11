package com.tqmall.athena.bean.entity.car;


import lombok.Data;

import java.io.Serializable;

/**
 * created by 钟熙耿 on 2015-09-22
 * 力洋id跟线上car的对应DO类
 */

@Data
public class CarAllDO implements Serializable {
    private Integer id;

    private String lId;

    private String company;

    private String brand;

    private String series;

    private String year;

    private String carType;

    private String carLevel;

    private String importInfo;

    private String power;

    private String newLId;

    private Integer brandId;

    private Integer seriesId;

    private Integer powerId;

    private Integer yearId;

    private String carModels;

    private Integer carModelsId;

    private String model;

    private Integer modelId;

}