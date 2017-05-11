package com.tqmall.athena.external.beans;

import lombok.Data;

import java.util.List;

/**
 * Created by huangzhangting on 16/6/16.
 */
@Data
public class VinServerResult {
    private boolean success;
    private String code;
    private String message;
    private List<String> data;
}
