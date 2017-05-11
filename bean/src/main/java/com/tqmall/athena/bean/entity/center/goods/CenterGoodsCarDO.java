package com.tqmall.athena.bean.entity.center.goods;

import lombok.Data;

import java.util.Date;

@Data
public class CenterGoodsCarDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer goodsId;

    private Integer carId;

    private Integer picId;

    private Integer subjoinId;

    private Integer modelId;

}