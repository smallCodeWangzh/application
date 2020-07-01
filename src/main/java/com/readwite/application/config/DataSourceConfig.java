package com.readwite.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    /**
     * 将创建的master数据源存入Spring容器中，并且注入内容
     * key值为方法名
     * @return master数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 将创建的slave数据源存入Spring容器中，并且注入内容
     * key值为方法名
     * @return slave数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 决定最终要使用的数据源
     * @return
     */
    @Bean
    public DataSource targetDataSource(@Qualifier("masterDataSource") DataSource masterDataSoure,
                                       @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        // 用来存放主数据源和从数据源
        Map<Object, Object> targetDataSource = new HashMap<>();

        // 往map中添加主数据源
        targetDataSource.put(DBTypeEnum.MASTER,masterDataSoure);

        // 往map中添加从数据源
        targetDataSource.put(DBTypeEnum.SLAVE,slaveDataSource);

        // 创建 routtingDataSource 用来实现动态切换
        RouttingDataSource routtingDataSource = new RouttingDataSource();

        // 绑定所有的数据源
        routtingDataSource.setTargetDataSources(targetDataSource);

        // 设置默认的数据源
        routtingDataSource.setDefaultTargetDataSource(masterDataSoure);

        return routtingDataSource;
    }

}
