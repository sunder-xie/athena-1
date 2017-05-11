package com.tqmall.athena.bean.entity.insurance;

import lombok.Data;

import java.util.Date;

@Data
public class InsuranceCompanyDO {
    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private String companyName;

    private String phone;

    private Short sort;


}