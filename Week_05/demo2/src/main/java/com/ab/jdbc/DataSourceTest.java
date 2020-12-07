package com.ab.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @classname: JDBCTest
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/18、22:33
 */
@Component
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    public int insert(String sql) throws SQLException {
        Connection connection = dataSource.getConnection();
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
