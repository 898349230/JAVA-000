package com.ab.week04.onmilliondata.service.impl;

import com.ab.week04.onmilliondata.service.IInsertOneMillionData;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @classname: BatchInsertOneMillionDataImpl 批量插入
 * @description:  分100次插入，每次插入 10000 条数据
 * @author: sunxinbo
 * @time: 2020/12/6、21:18
 */
@Service(value = "batchInsertOneMillionDataImpl")
public class BatchInsertOneMillionDataImpl implements IInsertOneMillionData {
    @Override
    public Long insert() throws SQLException {
        System.out.println("batchInsertOneMillionDataImpl start...");
        long start = System.currentTimeMillis();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false", "root", "123456");
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
