package com.tqmall.athena.bean.entity.center.goods;

import lombok.Data;

import java.util.Date;

@Data
public class CenterGoodsBaseDO {
    private Integer id;

    private String isDeleted;

    private Date gmtModified;

    private Date gmtCreate;

    private String creator;

    private String modifier;

    private Integer partId;

    private String partName;

    private String unit;

    private Integer firstCatId;

    private Integer secondCatId;

    private Integer thirdCatId;

    private String firstCatName;

    private String secondCatName;

    private String thirdCatName;

}