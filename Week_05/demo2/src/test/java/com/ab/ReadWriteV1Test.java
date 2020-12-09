package com.ab;

import com.ab.week04.onmilliondata.service.IInsertOneMillionData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @classname: InsertOneMillionDataTest 插入100万条数据测试类
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/6、21:31
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Demo2Application.class)
public class InsertOneMillionDataTest {

    @Autowired
    @Qualifier(value = "batchInsertOneMillionDataImpl")
    private IInsertOneMillionData batchInsertOneMillionDataImpl;

    @Autowired
    @Qualifier(value = "dataSourceBatchInsertOneMillionDataImpl")
    private IInsertOneMillionData dataSourceBatchInsertOneMillionDataImpl;

    @Autowired
    @Qualifier(value = "dataSourceOne2OneInsertOneMillionDataImpl")
    private IInsertOneMillionData dataSourceOne2OneInsertOneMillionDataImpl;

    @Autowired
    @Qualifier(value = "one2OneInsertMillionDataImpl")
    private IInsertOneMillionData one2OneInsertMillionDataImpl;

    @Test
    public void test() throws SQLException {
        Long insert1 = batchInsertOneMillionDataImpl.insert();
        Long insert2 = dataSourceBatchInsertOneMillionDataImpl.insert();
        Long insert3 = dataSourceOne2OneInsertOneMillionDataImpl.insert();
        Long insert4 = one2OneInsertMillionDataImpl.insert();
        System.out.println("批量： " + insert1);
        System.out.println("连接池批量： " + insert2);
        System.out.println("一对一： " + insert3);
        System.out.println("连接池一对一： " + insert4);

    }

}
