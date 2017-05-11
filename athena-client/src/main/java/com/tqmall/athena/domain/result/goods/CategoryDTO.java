package com.tqmall.athena.domain.result.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/5/25.
 */
@Data
public class CategoryDTO implements Serializable {
    private Integer catId; //主键id

    private String catName; //分类名称

    private Integer parentId; //父级id

    private Boolean isShow; //true：展示  false：不展示

    private Integer catKind; //0:易损件  1:全车件  2:二者无法区分

    private Integer catLevel; //分类级别：1:一级分类  2:二级分类  3:三级分类

    private String vehicleCode; //车辆种类码：C:乘用车  H:商用车
}
