package com.ab;

import com.ab.po.OrderPo;
import com.ab.service.IOrderservice;
import com.ab.week04.onmilliondata.service.IInsertOneMillionData;
import org.aspectj.weaver.ast.Or;
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
public class ReadWriteV1Test {

    @Autowired
    private IOrderservice orderservice;

    @Test
    public void testRead(){
//         读 db02
//        OrderPo orderById = orderservice.getOrderById(12962527L);
        OrderPo orderById = orderservice.getOrderById(6204937L);
        System.out.println(orderById);
    }

    @Test
    public void testWrite(){
//        写 db01
        OrderPo po = new OrderPo();
        po.setUserId(10001L);
        po.setGoodsId(102L);
        Long id = orderservice.insertOrder(po);
        System.out.println("id :" + id);
    }

}
