package com.tqmall.athena.domain.result.insurance;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 15/11/27.
 */
@Data
public class InsuranceCompanyDTO implements Serializable{
    private Integer id;

    private String companyName;  //公司名称

    private String phone;  //电话

    private Short sort;  //排序
}
