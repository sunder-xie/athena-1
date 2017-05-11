package com.tqmall.athena.common.redis.annotation;

import java.lang.annotation.*;

/**
 *
 * 使用例子:
 *
 *  @RedisCache(key="some_key")
 *  public List<Car> getCars(){
 *      some code ...
 *  };
 *
 * Created by huangzhangting on 17/4/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
    String key();

    int expire() default 3600; //默认1小时
}
