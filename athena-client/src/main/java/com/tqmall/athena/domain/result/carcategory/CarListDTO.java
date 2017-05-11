package com.tqmall.athena.domain.result.carcategory;

import com.tqmall.athena.domain.util.ShareConstantsUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * copy from stall
 * Created by jeremy on 14-7-2.
 */
@Data
public class CarListDTO implements Serializable{
    private Integer id;

    private String name;

    private String power;

    private Integer powerId;

    private Integer level;

    private String firstWord;

    private Integer pid;

    private Integer yearId;

    private String year;

    private String model;

    private Integer modelId;

    private String series;

    private Integer seriesId;

    private String brand;

    private Integer brandId;

    private String company;

    private String carType;

    private String importInfo;

    private String logo;

    private String carName;//拼装车型全称

    public String getLogo() {
        return ShareConstantsUtil.getImgUrl(logo);
    }
}
