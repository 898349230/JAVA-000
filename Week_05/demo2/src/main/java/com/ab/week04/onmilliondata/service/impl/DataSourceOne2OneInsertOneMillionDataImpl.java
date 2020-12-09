package com.ab.week04.onmilliondata.service.impl;

import com.ab.week04.onmilliondata.service.IInsertOneMillionData;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @classname: DataSourcePoolInsertOneMillionDataImpl
 * @description: 使用数据库连接池一条条插入数据
 * @author: sunxinbo
 * @time: 2020/12/6、21:25
 */
@Service(value = "dataSourceOne2OneInsertOneMillionDataImpl")
public class DataSourceOne2OneInsertOneMillionDataImpl implements IInsertOneMillionData {

    @Autowired
    @Qualifier(value = "dataSource01")
    private DataSource dataSource;

    @Override
    public Long insert() throws SQLException {
        System.out.println("dataSourceOne2OneInsertOneMillionDataImpl start...");
        long start = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            for (int i = 0; i < 1000000; i++) {
                String sql = "insert into t_order(goods_id,user_id,create_date) value("+i%500+","+i%30+",now())";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeBatch();
                preparedStatement.execute();
                connection.commit();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        System.out.println("batchInsertOneMillionDataImpl end...");
        long end = System.currentTimeMillis();
        return end-start;
    }
}
