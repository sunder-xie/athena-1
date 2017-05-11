package com.tqmall.athena.common.bean;

/**
 * Created by ximeng on 2015/4/15.
 * 
 */
public class HttpClientResult {

    private String result;
    private int statusCode;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}