package com.ab.config;

import com.ab.holder.ThreadHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @classname: MulitiDataSourceConfig 动态获取数据源
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/8、19:52
 */
public class MultiDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadHolder.threadLocal.get();
    }
}
