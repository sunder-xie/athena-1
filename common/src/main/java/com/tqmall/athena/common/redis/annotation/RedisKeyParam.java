package com.tqmall.athena.common.redis.annotation;

import java.lang.annotation.*;

/**
 *
 * 使用例子:
 *
 *  @RedisCache(key="some_key")
 *  public Car getCarById(@RedisKeyParam Integer id){
 *      some code ...
 *  };
 *
 * Created by huangzhangting on 17/4/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface RedisKeyParam {
}
