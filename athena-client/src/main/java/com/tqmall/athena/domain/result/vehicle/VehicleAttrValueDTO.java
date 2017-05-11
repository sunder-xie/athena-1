package com.tqmall.athena.domain.result.vehicle;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/5/10.
 */
@Data
public class VehicleAttrValueDTO implements Serializable{

    private String attrName;

    private String attrValue;
}
