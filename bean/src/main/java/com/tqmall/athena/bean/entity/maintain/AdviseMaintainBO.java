package com.tqmall.athena.bean.entity.maintain;

import lombok.Data;

import java.util.List;

/**
 * Created by huangzhangting on 15/11/13.
 */
@Data
public class AdviseMaintainBO {
    private Integer yearId;
    private Integer mileage;
    private List<String> items;
}
