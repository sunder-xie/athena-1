package com.tqmall.athena.bean.entity.center.car;

import com.tqmall.athena.bean.common.ShareConstantsUtil;
import lombok.Data;

import java.util.Date;

@Data
public class CenterCarDO {
    private Integer id;

    private String isDeleted;

    private Date gmtModified;

    private Date gmtCreate;

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

    public String getLogo() {
        return ShareConstantsUtil.getImgUrl(logo);
    }

}