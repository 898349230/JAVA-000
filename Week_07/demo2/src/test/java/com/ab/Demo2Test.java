package com.ab;

import com.ab.jdbc.DataSourceTest;
import com.ab.jdbc.JDBCTest;
import com.ab.service.ICacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;

/**
 * @classname: Demo2Test
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、22:56
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Demo2Application.class)
@WebAppConfiguration
public class Demo2Test {

    @Autowired
    private School school;
    @Autowired
    private ICacheService iCacheService;
    @Autowired
    private JDBCTest jdbcTest;
    @Autowired
    private DataSourceTest dataSourceTest;

    /**
     * @description 测试 shcool-spring-boot-starter 是否装配
     * @param
     * @return void
     * @author sunxinbo
     * @time 2020/11/17 22:58
     */   
    @Test
    public void test(){
        System.out.println(school);
    }

    @Test
    public void testJDBC() throws SQLException {
        System.out.println(jdbcTest.insert("insert into person values(6,13)"));
    }

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSourceTest.insert("insert into person values(7,13)"));
    }

    @Test
    public void testCacheAnnotation() throws InterruptedException {
        System.out.println(iCacheService.getString("第一次 "));
        Thread.sleep(6000);
        System.out.println(iCacheService.getString("第二次 "));
    }
}
