package com.demo.doubledatasource.config;

import java.lang.annotation.*;


/**
 * @title 多数据源注解
 * @author zengzp
 * @time 2018年7月25日 下午14:50:53
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name() default "";
}
