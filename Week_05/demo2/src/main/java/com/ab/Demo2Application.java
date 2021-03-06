package com.ab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @classname: com.ab.Demo2Application
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、22:38
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.ab.mapper")
public class Demo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class);
    }
}
