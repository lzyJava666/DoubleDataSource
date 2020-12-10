package com.demo.doubledatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.annotation.Repeatable;


/**
 * @title 多事物管理器配置
 * @author zengzp
 * @time 2018年7月25日 下午4:55:33
 * @Description
 */
//@Configuration
public class TransactionConfig {

    public final static String TEST1_TX = "test1TX";

    public final static String TEST2_TX = "test2TX";


    @Bean(name=TransactionConfig.TEST1_TX)
    public DataSourceTransactionManager transaction(@Qualifier(DataSourceNames.TEST1) DataSource test1DataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(test1DataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name=TransactionConfig.TEST2_TX)
    public DataSourceTransactionManager rongyuanTransaction(@Qualifier(DataSourceNames.TEST2) DataSource test2DataScoure){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(test2DataScoure);
        return dataSourceTransactionManager;
    }

}
