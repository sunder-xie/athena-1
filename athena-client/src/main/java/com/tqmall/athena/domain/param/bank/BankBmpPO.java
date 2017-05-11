package com.tqmall.athena.domain.param.bank;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lyj on 16/4/25.
 */
@Data
public class BankBmpPO implements Serializable {
    private String keyword;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

}
