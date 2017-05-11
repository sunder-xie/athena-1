package com.tqmall.athena.domain.result.center.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/6/23.
 */
@Data
public class CenterGoodsCarDTO implements Serializable {
    private Integer id;

    private Integer goodsId;

    private Integer carId;

    private Integer picId;

    private Integer subjoinId;

    private Integer modelId;
}
