package com.readwite.application.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 决定返回哪个数据源的key
 */
public class RouttingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        /**
         * 返回当前线程正在使用的代表数据库的枚举对象
         */
        return DynamicSwitchDBTypeUtil.get();
    }
}
