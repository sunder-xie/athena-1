package com.tqmall.athena.bean.entity.maintain;

import lombok.Data;

import java.util.Date;

@Data
public class MaintainItemDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private String name;

    private String unit;

    private String suggest;

    private Integer sort;

    /*
    * 是否适用于全部车型
    * true：所有车型该保养项目的保养规则一致，根据 firstMileage、intervalMileage 计算即可；
    *       当firstMileage、intervalMileage都为0时，该保养项与里程无关
    *
    * false：需要查看 ModelMaintainRelationDO
    * */
    private Boolean isCommon;

    private Integer firstMileage; //首次保养里程

    private Integer intervalMileage; //保养里程间隔

}