package com.tqmall.athena.domain.param.maintain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 15/11/13.
 */
@Data
public class CarMaintainPO implements Serializable {
    private Integer yearId;
    private Integer mileage;
}
