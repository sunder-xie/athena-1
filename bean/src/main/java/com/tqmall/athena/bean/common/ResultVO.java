package com.tqmall.athena.bean.common;


import lombok.Getter;
import lombok.Setter;

/**
 * 新版包装result
 * Created by yangrui on 14-5-28.
 */
public class ResultVO<D> {

    @Getter
    @Setter
    private boolean success;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private D data;

    // -------------------------- STATIC METHODS --------------------------

    public static <D> ResultVO<D> wrapSuccessfulResult(D data) {
        ResultVO<D> result = new ResultVO<D>();
        result.data = data;
        result.success = true;
        return result;
    }


    public static <D> ResultVO<D> wrapErrorResult(ServiceError error) {
        ResultVO<D> result = new ResultVO<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMessage();
        return result;
    }


    /**
     * 错误Message中不要补充信息时的构造函数
     * @return
     */
    public static <D> ResultVO<D> wrapErrorResult(ServiceError error,  Object... extendMsg) {
        ResultVO<D> result = new ResultVO<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = String.format(error.getMessage(), extendMsg);
        return result;
    }

    public static <D> ResultVO<D> wrapErrorResult(String code, String message) {
        ResultVO<D> result = new ResultVO<D>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }
}
