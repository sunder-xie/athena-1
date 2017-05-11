package com.tqmall.athena.bean.entity.goods;

import lombok.Data;

@Data
public class CategoryBrandDO {
    private Integer id;

    private Integer cateId;

    private String cateName;

    private Integer brandId;

    private String brandName;

    private Boolean isDeleted;

}