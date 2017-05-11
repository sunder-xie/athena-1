package com.tqmall.athena.domain.result.center.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/2/17.
 */
@Data
public class CenterGoodsDTO implements Serializable {
    private Integer id;

    private String oeNumber;

    private Integer brandId;

    private String goodsFormat;

    private String goodsPic;

    private String goodsUnit;

    private Integer thirdCateId;

    private String partName;
}
