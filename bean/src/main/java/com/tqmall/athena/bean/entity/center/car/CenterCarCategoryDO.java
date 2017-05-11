package com.tqmall.athena.bean.entity.center.car;

import lombok.Data;

import java.util.Date;

@Data
public class CenterCarCategoryDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer carId;

    private Integer firstCatId;

    private Integer secondCatId;

    private Integer thirdCatId;

    private String firstCatName;

    private String secondCatName;

    private String thirdCatName;

    private String firstPic;

    private String secondPic;

    private String thirdPic;

    private String firstCatLetter;

    private String secondCatLetter;

    private String thirdCatLetter;

    private String vehicleCode;

}