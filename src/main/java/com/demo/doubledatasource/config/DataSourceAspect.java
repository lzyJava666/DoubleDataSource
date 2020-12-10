package com.demo.doubledatasource.config;

import java.lang.reflect.Method;
import java.sql.SQLOutput;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 * @title 多数据源切面处理类
 * @author zengzp
 * @time 2018年7月25日 下午11:56:43
 * @Description
 */
@Aspect
@Component
@Order(0)
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.demo.doubledatasource.config.TargetDataSource)")
    public void dataSourcePointCut() {

    }

    @Before("dataSourcePointCut()")
    public void around(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        TargetDataSource ds = method.getAnnotation(TargetDataSource.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.TEST1);
            logger.debug("set datasource is " + DataSourceNames.TEST2);
        }else {
            DynamicDataSource.setDataSource(ds.name());
            System.out.println(ds.name());
            logger.debug("set datasource is " + ds.name());
        }
    }

    @AfterReturning("dataSourcePointCut()")
    public void after(){
        DynamicDataSource.clearDataSource();
        logger.debug("clean datasource");
    }

    @AfterReturning("execution(* com.demo.doubledatasource.service.*.*(..))")
    public void CutAfter(){
        DynamicDataSource.clearDataSource();
        logger.debug("clean datasource");
    }

}
