package com.tqmall.athena.domain.result.center.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/6/23.
 */
@Data
public class CenterGoodsCarSubjoinDTO implements Serializable {
    private Integer id;

    private String goodsCarRemarks;

    private Integer applicationAmount;
}
