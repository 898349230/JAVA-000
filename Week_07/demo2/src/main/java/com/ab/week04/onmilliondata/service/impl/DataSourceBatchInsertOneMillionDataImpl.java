package com.ab.week04.onmilliondata.service.impl;

import com.ab.week04.onmilliondata.service.IInsertOneMillionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @classname: DataSourcePoolInsertOneMillionDataImpl
 * @description: 使用数据库连接池批量插入数据
 * @author: sunxinbo
 * @time: 2020/12/6、21:25
 */
@Service(value = "dataSourceBatchInsertOneMillionDataImpl")
public class DataSourceBatchInsertOneMillionDataImpl implements IInsertOneMillionData {

    @Autowired
    @Qualifier(value = "dataSource01")
    private DataSource dataSource;

    @Override
    public Long insert() throws SQLException {
        System.out.println("dataSourceBatchInsertOneMillionDataImpl start...");
        long start = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            for (int i = 0; i < 100; i++) {
                StringBuilder sql = new StringBuilder("insert into t_order(goods_id,user_id,create_date) values");
                for (int i1 = 0; i1 < 10000; i1++) {
                    sql.append("("+i1%500+","+i1%30+",now()),");
                }
                PreparedStatement preparedStatement = connection.prepareStatement(sql.substring(0, sql.length()-1));
                preparedStatement.execute();
            }
            connection.commit();
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
