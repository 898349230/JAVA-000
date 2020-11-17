package com.ab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @classname: ApplicationTest
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、22:03
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ApplicationTest {

    @Autowired
    private School school;

    @Test
    public void test(){
        System.out.println(school);
    }
}
