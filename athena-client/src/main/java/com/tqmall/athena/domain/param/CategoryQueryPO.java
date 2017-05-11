package com.tqmall.athena.domain.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 16/5/25.
 */
@Data
public class CategoryQueryPO implements Serializable{
    private Integer id; //主键id

    private Integer pid; //父级id

    private String vehicleCode; //C:乘用车  H:商用车

    private Integer isShow; //1：展示   0：不展示

    private Integer level; //1：一级类目  2：二级类目  3：三级类目
}
