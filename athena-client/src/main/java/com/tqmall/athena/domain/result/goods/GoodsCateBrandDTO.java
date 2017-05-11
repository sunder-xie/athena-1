package com.tqmall.athena.domain.result.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 15/10/22.
 */
@Data
public class GoodsCateBrandDTO implements Serializable {
    private Integer brandId;
    private String brandName;
}
