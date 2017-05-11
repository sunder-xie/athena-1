package com.tqmall.athena.bean.bizBean.car;

import lombok.Data;

/**
 * Created by huangzhangting on 16/6/15.
 */
@Data
public class CarBO {
    private Integer id; //level 6级别id
    private String name; //level 6级别名称
    private Integer yearId;
    private String year;
    private Integer powerId;
    private String power;
    private Integer modelId;
    private String model;
    private Integer seriesId;
    private String series;
    private Integer brandId;
    private String brand;
    private String carName; //拼接的名称

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", carName=" + carName +
                '}';
    }
}
