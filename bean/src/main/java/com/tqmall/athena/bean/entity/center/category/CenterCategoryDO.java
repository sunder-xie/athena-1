package com.tqmall.athena.bean.entity.center.category;

import lombok.Data;

import java.util.Date;

@Data
public class CenterCategoryDO {

    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private String firstLetter;

    private String catName;

    private Integer parentId;

    private Integer sortOrder;

    private String catPic;

    private Integer catKind;

    private Integer catLevel;

    private String catCode;

    private String vehicleCode;

}