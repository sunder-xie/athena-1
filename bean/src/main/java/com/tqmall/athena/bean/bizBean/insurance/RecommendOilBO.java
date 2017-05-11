package com.tqmall.athena.bean.bizBean.insurance;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 推荐机油，数据封装对象
 * Created by huangzhangting on 16/9/21.
 */
@Data
public class RecommendOilBO{
    private String goodsSn; //商品编码
    private BigDecimal oilCapacity; //机油用量

    @Override
    public String toString() {
        return "{" +
                "goodsSn=" + goodsSn +
                ", oilCapacity=" + oilCapacity +
                '}';
    }
}
