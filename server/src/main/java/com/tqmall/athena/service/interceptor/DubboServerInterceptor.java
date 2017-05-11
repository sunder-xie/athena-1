package com.tqmall.athena.service.interceptor;

import com.alibaba.dubbo.rpc.RpcContext;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by huangzhangting on 17/3/20.
 */
@Slf4j
public class DubboServerInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            beforeProceed(invocation);
            return invocation.proceed();
        }catch (Exception e){
            return exceptionProcessor(invocation, e);
        }
    }

    private void beforeProceed(MethodInvocation invocation){
        //获取调用方IP地址
        String clientIp = RpcContext.getContext().getRemoteHost();
        StringBuilder sb = new StringBuilder(150);
        sb.append("\ninvoke dubbo server. clientIp=").append(clientIp);
        sb.append(",").append(getInvocationInfo(invocation));
        log.info(sb.toString());
    }

    private Object exceptionProcessor(MethodInvocation invocation, Exception e){
        String errorInfo = "\ninvoke dubbo server failed."+getInvocationInfo(invocation);
        log.error(errorInfo, e);

        return ResultUtil.errorResult("", e.getMessage());
    }

    private String getInvocationInfo(MethodInvocation invocation){
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        String methodName = method.getName();
        Class<?> declaringClass = method.getDeclaringClass();
        String className = declaringClass.getSimpleName();

        StringBuilder sb = new StringBuilder(100);
        sb.append(" class=").append(className);
        sb.append(", method=").append(methodName);
        sb.append(", params=").append(JsonUtil.objectToJsonStr(args));
        return sb.toString();
    }

}
