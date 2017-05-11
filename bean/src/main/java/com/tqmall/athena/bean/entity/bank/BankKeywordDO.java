package com.tqmall.athena.bean.entity.bank;

import lombok.Data;

import java.util.Date;

@Data
public class BankKeywordDO {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private String isDeleted;

    private String keyword;

    private Integer sortNum;

}