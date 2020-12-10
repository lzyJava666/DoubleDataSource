package com.demo.doubledatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author zengzp
 * @title
 * @time 2018年7月25日 上午11:22:46
 * @Description
 */
@Configuration
// 加上此注解禁用数据源自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DynamicDataSourceConfig {

    @Bean(name = DataSourceNames.TEST1)
    @ConfigurationProperties("spring.datasource.druid.test1")
    public DataSource firstDataSource() {

        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceNames.TEST2)
    @ConfigurationProperties("spring.datasource.druid.test2")
    public DataSource secondDataSource() {

        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier(DataSourceNames.TEST1) DataSource test1DataSource,@Qualifier(DataSourceNames.TEST2) DataSource test2DataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.TEST1, test1DataSource);
        targetDataSources.put(DataSourceNames.TEST2, test2DataSource);
        return new DynamicDataSource(test1DataSource, targetDataSources);
    }

    public final static String TEST1_TX = "test1TX";

    public final static String TEST2_TX = "test2TX";


    @Bean(name=TransactionConfig.TEST1_TX)
    public DataSourceTransactionManager test1Transaction(@Qualifier(DataSourceNames.TEST1) DataSource test1DataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(test1DataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name=TransactionConfig.TEST2_TX)
    public DataSourceTransactionManager test2Transaction(@Qualifier(DataSourceNames.TEST2) DataSource test2DataScoure){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(test2DataScoure);
        return dataSourceTransactionManager;
    }

}
