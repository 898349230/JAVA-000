package com.ab.config;

import com.ab.holder.ThreadHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @classname: MulitiDataSourceConfig
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/8、19:52
 */
public class MulitiDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadHolder.threadLocal.get();
    }
}
