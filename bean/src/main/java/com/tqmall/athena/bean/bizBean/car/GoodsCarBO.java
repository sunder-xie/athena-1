package com.tqmall.athena.bean.bizBean.car;

import lombok.Data;

/**
 * Created by huangzhangting on 16/10/19.
 */
@Data
public class GoodsCarBO {
    private Integer carModelId; //车型id
    private String carModel; //车型
    private Integer carSeriesId; //车系id
    private String carSeries; //车系
    private String company; //厂家
    private String carBrand; //车品牌
    private String brandLogo; //品牌logo
    private String brandFirstWord; //品牌首字母
    private String importInfo; //进口合资国产

    private Integer carId; //车款id level 6级别
    private String carName; //车款名称
}
