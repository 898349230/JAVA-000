package com.ab.week04.onmilliondata.service.impl;

import com.ab.week04.onmilliondata.service.IInsertOneMillionData;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @classname: insertOneMillionDataImpl
 * @description: 一条条插入, 每次开启新的数据库连接，开启事务
 * @author: sunxinbo
 * @time: 2020/12/6、21:01
 */
@Service(value = "one2OneInsertMillionDataImpl")
public class One2OneInsertMillionDataImpl implements IInsertOneMillionData {

    @Override
    public Long insert() throws SQLException{
        System.out.println("one2OneInsertMillionDataImpl start...");
        long start = System.currentTimeMillis();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false", "root", "123456");
        connection.setAutoCommit(false);
        try {
            for (int i1 = 0; i1 < 1_000_000; i1++) {
                String sql = "insert into t_order(goods_id,user_id,create_date) value("+i1%500+","+i1%30+",now())";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
