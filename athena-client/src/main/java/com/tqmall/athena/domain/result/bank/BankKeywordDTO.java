package com.tqmall.athena.domain.result.bank;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lyj on 16/4/25.
 */
@Data
public class BankKeywordDTO implements Serializable {
    private String keyword;

    private Integer sortNum;

}
