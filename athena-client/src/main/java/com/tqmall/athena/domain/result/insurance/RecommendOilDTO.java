package com.tqmall.athena.domain.result.insurance;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 推荐机油，数据封装对象
 * Created by huangzhangting on 16/9/21.
 */
@Data
public class RecommendOilDTO implements Serializable {
    private String goodsSn; //商品编码
    private BigDecimal oilCapacity; //机油用量
}
