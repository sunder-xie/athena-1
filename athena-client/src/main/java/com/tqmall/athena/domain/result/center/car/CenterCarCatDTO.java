package com.tqmall.athena.domain.result.center.car;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CenterCarCatDTO implements Serializable{
    private Integer id;

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