package com.tqmall.athena.bean.entity.goods;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsCategoryDO {
    private Integer catId;

    private Integer goodsType;

    private String catName;

    private String keywords;

    private String catDesc;

    private Integer parentId;

    private Integer sortOrder;

    private String measureUnit;

    private Boolean isShow;

    private String categoryThumb;

    private String categoryImg;

    private String originalImg;

    private String categorySn;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private String isDeleted;

    private Integer catKind;

    private Integer catLevel;

    private String catCode;

    private String vehicleCode;

}