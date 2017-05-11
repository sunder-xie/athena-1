package com.tqmall.athena.common.bean;

import com.tqmall.core.common.errorcode.ErrorCode;
import com.tqmall.core.common.errorcode.ErrorCodeBuilder;
import com.tqmall.core.common.errorcode.PlatformErrorCode;
import com.tqmall.core.common.errorcode.support.CommonError;

/**
 * athena项目异常定义
 * Created by huangzhangting on 16/1/25.
 */
public class DataError extends CommonError {
    private static final String ATHENA_CODE = "00";

    public static final ErrorCode NO_DATA_ERROR = ErrorCodeBuilder.newError(PlatformErrorCode.DATA, ATHENA_CODE)
            .setWarn()
            .setDetailCode("0001")
            .setName("数据缺失")
            .setMessageFormat("数据缺失，补充中")
            .genErrorCode();

    public static final ErrorCode UNKNOW_EXCEPTION = ErrorCodeBuilder.newError(PlatformErrorCode.DATA, ATHENA_CODE)
            .setError()
            .setDetailCode("0002")
            .setName("未知异常")
            .setMessageFormat("系统发生未知异常")
            .genErrorCode();

}
