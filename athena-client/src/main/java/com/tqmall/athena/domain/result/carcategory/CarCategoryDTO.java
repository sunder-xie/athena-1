package com.tqmall.athena.domain.result.carcategory;

import lombok.Data;

import java.io.Serializable;

/**
 * copy from stall
 * Created by chenjinlong on 15/5/19.
 */
@Data
public class CarCategoryDTO implements Serializable {
    private Integer id;

    private String name;

    private String power;

    private Integer level;

    private String firstWord;

    private Integer sort;

    private Integer pid;

    private Integer isDel;

    private String series;

    private String brand;

    private String company;

    private Byte country;

    private String carType;

    private String carLevel;

    private String importInfo;

    private String logo;

    private Integer isHot;

    //新增属性
    //云修需求
    private String guidePrice;
    //档口web需求
    private String model;
    private String year;

}
