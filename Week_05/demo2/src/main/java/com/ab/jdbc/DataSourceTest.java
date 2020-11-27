package com.ab.jdbc;

import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @classname: JDBCTest
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/18、22:33
 */
@Component
public class JDBCTest {

    public int insert(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://myaliyun.com:3309/db01", "root", "123456");
        connection.setAutoCommit(false);
        int i = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            i = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return i;
    }
}
