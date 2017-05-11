package com.tqmall.athena.domain.result.maintain;

import lombok.Data;

import java.io.Serializable;

/**
 * 新版保养项目对象
 */
@Data
public class MaintainItemDTO implements Serializable {
    private Integer id;

    private String name;

    private String unit; //保养项目单位

    private String suggest; //保养建议

    private Integer sort;

    /*
    * 是否适用于全部车型
    * true：所有车型该保养项目的保养规则一致，根据 firstMileage、intervalMileage 计算即可；
    *       当firstMileage、intervalMileage都为0时，该保养项与里程无关
    *
    * false：需要查看 MaintainDetailDTO
    * */
    private Boolean isCommon;

    private Integer firstMileage; //首次保养里程

    private Integer intervalMileage; //保养里程间隔

}
