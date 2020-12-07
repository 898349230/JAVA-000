package com.ab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @classname: DataSourceConfiguration
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/6、22:05
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    public DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db01");
        config.setUsername("root");
        config.setPassword("123456");
//        minimum-idle: 5
//#      maximum-pool-size: 15
        config.setMaximumPoolSize(50);
        config.setMinimumIdle(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
