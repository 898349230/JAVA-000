package com.ss.example.ssjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ss.example.ssjdbc.mapper")
public class SsJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsJdbcApplication.class, args);
    }

}
