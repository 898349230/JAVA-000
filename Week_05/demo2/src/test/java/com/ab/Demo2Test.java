package com.ab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @classname: Demo2Test
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、22:56
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Demo2Application.class)
public class Demo2Test {

    @Autowired
    private School school;

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
}
