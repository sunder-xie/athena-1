package com.tqmall.athena.domain.result.maintain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangzhangting on 15/11/13.
 */
@Data
public class AdviseMaintainDTO implements Serializable{
    private Integer yearId;
    private Integer mileage;
    private List<String> items;
}
