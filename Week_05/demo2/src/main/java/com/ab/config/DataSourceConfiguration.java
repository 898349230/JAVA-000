package com.ab.config;

import com.ab.Constants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @classname: DataSourceConfiguration
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/6、22:05
 */
@Configuration
public class DataSourceConfiguration {

    @Bean(value = "dataSource01")
    public DataSource getDataSource01(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db01");
        config.setUsername("root");
        config.setPassword("123456");
        config.setMaximumPoolSize(50);
        config.setMinimumIdle(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    @Bean(value = "dataSource02")
    public DataSource getDataSource02(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db02");
        config.setUsername("root");
        config.setPassword("123456");
        config.setMaximumPoolSize(50);
        config.setMinimumIdle(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    /**
     * @description 配置动态数据源
     * @param
     * @return com.ab.config.MultiDataSource
     * @author sunxinbo
     * @time 2020/12/10 0:28
     */
    @Bean(value = "dataSource")
    @Primary
    public MultiDataSource getDataSource(){
        HikariConfig config1 = new HikariConfig();
        config1.setJdbcUrl("jdbc:mysql://localhost:3306/db01");
        config1.setUsername("root");
        config1.setPassword("123456");
        config1.setMaximumPoolSize(50);
        config1.setMinimumIdle(5);
        config1.addDataSourceProperty("cachePrepStmts", "true");
        config1.addDataSourceProperty("prepStmtCacheSize", "250");
        config1.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariConfig config2 = new HikariConfig();
        config2.setJdbcUrl("jdbc:mysql://localhost:3306/db02");
        config2.setUsername("root");
        config2.setPassword("123456");
        config2.setMaximumPoolSize(50);
        config2.setMinimumIdle(5);
        config2.addDataSourceProperty("cachePrepStmts", "true");
        config2.addDataSourceProperty("prepStmtCacheSize", "250");
        config2.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        MultiDataSource multiDataSource = new MultiDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(Constants.INSERT_DATASOURCE_NAME, new HikariDataSource(config1));
        dataSourceMap.put(Constants.READ_DATASOURCE_NAME, new HikariDataSource(config2));
        multiDataSource.setTargetDataSources(dataSourceMap);
        return multiDataSource;
    }
}
