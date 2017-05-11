package com.tqmall.athena.bean.entity.maintain;

import lombok.Data;

import java.util.Date;

/**
 * Created by huangzhangting on 15/9/21.
 */
@Data
public class MaintainDetail {

    //relation表相关字段
    private Integer modelId;

    private Date firstTime;

    private Integer mile;

    private Integer serviceId;

    private Integer serviceType;

    private String serviceSuggest;

    private String serviceUse;

    private String timeConsuming;

    private Double price;

    //item相关字段
    private String serviceName;

    private String unit;

    private String suggest;

    private Integer sort;

}
