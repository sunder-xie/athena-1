package com.tqmall.athena.bean.entity.center.goods;

import lombok.Data;

import java.util.Date;

@Data
public class CenterGoodsAttrDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer goodsId;

    private Integer attrId;

    private String attrName;

    private String attrValue;

}