package com.ab.anotation;

import java.lang.annotation.*;

/**
 * @description 配置方法返回值缓存
 * @return
 * @author sunxinbo
 * @time 2020/11/17 23:25
 */   
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyCache {
    int time() default 60;
}
