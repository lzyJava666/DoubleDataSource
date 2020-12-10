package com.demo.doubledatasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//手动切换数据源
@Component
public class CutDataSource {

    protected static Logger logger = LoggerFactory.getLogger(CutDataSource.class);

    public static void useDataSource(String dataName) {
        if (dataName == null) {
            DynamicDataSource.setDataSource(DataSourceNames.TEST1);
            logger.debug("set datasource is " + DataSourceNames.TEST1);
        } else {
            DynamicDataSource.setDataSource(dataName);
            System.out.println(dataName);
            logger.debug("set datasource is " + dataName);
        }
    }


}
