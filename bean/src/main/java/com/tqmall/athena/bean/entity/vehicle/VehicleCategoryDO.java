package com.tqmall.athena.bean.entity.vehicle;

import lombok.Data;

@Data
public class VehicleCategoryDO {
    public static final int INIT_ID = 500000; //数据库中的初始id

    private Integer id;

    private Integer pid;

    private String categoryName;

    private Integer grade; //级别，1：一级分类、2：二级分类

    private Integer sort;

}