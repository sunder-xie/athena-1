package com.tqmall.athena.controller.tools;

import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 15/10/20.
 */

@RestController
@RequestMapping("/tools")
@Slf4j
public class ToolsController {


    @RequestMapping(value = "/dubbo/service",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Object invokeDubboService(@RequestBody DubboParam param) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        String service = param.getService().trim();
        try {
            Class<?> clazz = Class.forName(service);
            Object o = wac.getBean(clazz);

            List<Param> params = param.getParams();
            if(params != null) {
                Integer size = params.size();
                Class<?>[] parameterTypes = new Class[size];
                Object[] args = new Object[size];
                for(int i=0; i< size; ++i) {
                    Param p = params.get(i);
                    Class<?> paramClazz = Class.forName(p.getParamType());
                    parameterTypes[i] = paramClazz;
                    if(ClassUtil.isPrimtiveType(paramClazz)) {
                        Class<?> targetClass = ClassUtil.getPrimtiveTypeWrapperClass(paramClazz);
                        Constructor<?> ctor = targetClass.getConstructor(String.class);
                        args[i] = ctor.newInstance(p.getParam());
                    } else {
                        Object paramObj = JsonUtil.jsonStrToObject(p.getParam(), paramClazz);
                        args[i] = paramObj;
                    }
                }
                //获得类的特定方法，name参数指定方法的名字，parameterTypes 参数指定方法的参数类型。
                Method method = clazz.getMethod(param.getMethod(), parameterTypes);
                Object result =  method.invoke(o, args);
                return result;
            } else {
                Method method = clazz.getMethod(param.getMethod());
                Object result = method.invoke(o);
                return result;
            }

        } catch (Exception e) {
            log.error("调用dubbo服务["+service+"]异常：", e);

            return Result.wrapErrorResult(null, e.getMessage());
        }
    }



    @RequestMapping(value = "/dubbo/service/view",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public Object viewDubboServiceMethod(@RequestBody String service) {
        try {
            List<MethodInfo> methodDescList = new ArrayList<>();
            Class<?> clazz = Class.forName(service.trim());

            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods) {
                MethodInfo methodDesc = new MethodInfo();
                methodDescList.add(methodDesc);
                methodDesc.setMethodName(method.getName());

                Class<?>[] paramTypesArray = method.getParameterTypes();
                if(paramTypesArray != null && paramTypesArray.length > 0) {
                    List<ParamInfo> paramTypes = new ArrayList<>();
                    for(Class<?> paramTypeClass : paramTypesArray) {
                        ParamInfo p = new ParamInfo();
                        paramTypes.add(p);
                        p.setParamType(paramTypeClass.getName());
                        if(!ClassUtil.isPrimtiveType(paramTypeClass)) {
                            Object paramTypeInstance = null;
                            try {
                                paramTypeInstance = paramTypeClass.newInstance();
                            } catch(Exception ignore) {
                                // 不存在无参数构造函数，只是不给出参数demo
                            }
                            if(paramTypeInstance != null) {
                                String json = JsonUtil.objectToJsonStr(paramTypeInstance);
                                p.setParamDemo(json);
                            }
                        }
                    }
                    methodDesc.setParamTypes(paramTypes);
                }
            }
            return methodDescList;

        } catch (Exception e) {
            log.error("调用dubbo服务["+service+"]异常：", e);
            return Result.wrapErrorResult(null, e.getMessage());
        }

    }
}
