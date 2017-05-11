package com.tqmall.athena.bean.bizBean.params;

import lombok.Data;

/**
 * Created by huangzhangting on 16/5/25.
 */
@Data
public class CategoryQueryParam {
    private Integer id;

    private Integer pid;

    private Integer level;

    private String vehicleCode;

    private Integer isShow;
}
