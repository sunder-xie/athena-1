package com.tqmall.athena.dal;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * MyBatis AO{@link org.mybatis.spring.mapper.MapperScannerConfigurer}
 * 暂时没用，可配置在mybatis.xml中
 *         <property name="annotationClass" value="com.athena.monkey.dal.MyBatisRepository"/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
	String value() default "";
}
