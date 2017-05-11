package com.tqmall.athena.bean.entity.center.goods;

import lombok.Data;

import java.util.Date;

@Data
public class CenterGoodsCarPictureDO {
    private Integer id;

    private Integer createUserId;

    private Integer updateUserId;

    private Date gmtModified;

    private Date gmtCreate;

    private String epcPic;

    private String epcPicNum;

    private String epcIndex;


}