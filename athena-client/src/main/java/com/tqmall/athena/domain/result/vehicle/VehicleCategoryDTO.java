package com.tqmall.athena.domain.result.vehicle;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/3/23.
 */
@Data
public class VehicleCategoryDTO implements Serializable {
    private Integer id;

    private Integer pid;

    private String categoryName; //名称

    private Integer grade; //级别，1：一级分类、2：二级分类

    private Integer sort;
}
