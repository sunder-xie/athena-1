package com.tqmall.athena.domain.result.maintain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 15/9/21.
 */
@Data
public class ItemDTO implements Serializable {
    private Integer id;

    private String name;

    private String unit;

    private String suggest;

    private Integer sort;

}
