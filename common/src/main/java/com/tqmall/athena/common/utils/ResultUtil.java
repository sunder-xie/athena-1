package com.tqmall.athena.common.utils;

import com.tqmall.athena.common.bean.DataError;
import com.tqmall.core.common.entity.Result;
import com.tqmall.core.common.errorcode.ErrorCode;

import java.util.Collection;
import java.util.List;

/**
 * 返回结果工具类
 * Created by huangzhangting on 16/1/25.
 */
public class ResultUtil {
    public static final String SUCCESS_CODE = "00000000";

    public static <D> Result<D> errorResult(String code, String message){
        return Result.wrapErrorResult(code, message);
    }

    public static <D> Result<D> errorResult(ErrorCode errorCode){
        return Result.wrapErrorResult(errorCode.getCode(), errorCode.getErrorMessage());
    }

    public static <D> Result<D> errorResult(Result result){
        return Result.wrapErrorResult(result.getCode(), result.getMessage());
    }

    public static <D> Result<D> successResult(D data) {
        Result<D> result = new Result<>();
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static <D> Result<D> successResult(D data, String message) {
        Result<D> result = new Result<>();
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static <DO, BO> Result<List<BO>> successResult4List(Collection<DO> collection, Class<BO> boClass) {
        return successResult(BdUtil.do2bo4List(collection, boClass));
    }

    public static <DO, BO> Result<List<BO>> handleResult4List(Result<List<DO>> result, Class<BO> boClass){
        if(result.isSuccess()){
            return successResult(BdUtil.do2bo4List(result.getData(), boClass));
        }
        return errorResult(result);
    }

    public static <DO, BO> Result<BO> handleResult(Result<DO> result, Class<BO> boClass){
        if(result.isSuccess()){
            return successResult(BdUtil.do2bo(result.getData(), boClass));
        }
        return errorResult(result);
    }

    //可以详细区别具体异常，返回相应的异常错误
    public static <D> Result<D> handleException(Exception e){
        if(e instanceof NullPointerException){
            return errorResult(DataError.NPE_ERROR);
        }
        if(e instanceof IndexOutOfBoundsException){
            return errorResult(DataError.ARRAY_OUT_OF_BOUNDS);
        }


        return errorResult(DataError.UNKNOW_EXCEPTION);
    }

}
