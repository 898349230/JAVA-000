package com.ab.interceptor;

import com.ab.anotation.MyCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @classname: CacheServiceInterceptor
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、23:30
 */
@Aspect
@Component
public class CacheServiceInterceptor {

    private final ConcurrentHashMap<String, Long> method2ExpireTime = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> method2Result = new ConcurrentHashMap<>();

    @Pointcut(value = "execution(* com.ab.service.impl.*Impl.*(..))")
    public void aroundService(){

    }
    @Around(value = "aroundService()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        方法签名
        Signature signature = joinPoint.getSignature();
//        方法调用的参数
        Object[] args = joinPoint.getArgs();
        Class[] paraTypeArr = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            paraTypeArr[i] = args[i].getClass();
        }
//      方法名
        String methodName = signature.getName();
        Method method = signature.getDeclaringType().getMethod(methodName,paraTypeArr);
        MyCache annotation = method.getAnnotation(MyCache.class);
        if(null != annotation){
            int time = annotation.time();
            Long expireTime = method2ExpireTime.get(methodName);
//            存在 并且未过期
            if(null != expireTime && expireTime > System.currentTimeMillis()){
                System.out.println("缓存中取值。。。");
                return method2Result.get(methodName);
            }else{
                System.out.println("执行方法取值。。。");
                Object proceed = joinPoint.proceed();
                method2Result.put(methodName,proceed);
//                缓存值
                method2ExpireTime.put(methodName, System.currentTimeMillis()+time*1000);
                return proceed;
            }
        }
        return joinPoint.proceed();
    }

}
