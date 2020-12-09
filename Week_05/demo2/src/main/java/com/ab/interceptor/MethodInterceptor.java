package com.ab.interceptor;

import com.ab.Constants;
import com.ab.holder.ThreadHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @classname: MethodInterceptor
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/9、22:52
 */
@Aspect
@Component
public class MethodInterceptor {

    /**
     * @description 读 方法切点
     * @param
     * @return void
     * @author sunxinbo
     * @time 2020/12/9 22:55
     */
    @Pointcut(value = "execution(* com.ab.service.impl.*Impl.get*(..))")
    public void getAroundService(){

    }
    /**
     * @description 写方法切点
     * @param
     * @return void
     * @author sunxinbo
     * @time 2020/12/9 22:55
     */
    @Pointcut(value = "execution(* com.ab.service.impl.*Impl.insert*(..))")
    public void insertAroundService(){

    }

    @Around(value = "getAroundService()")
    public Object getAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        ThreadHolder.threadLocal.set(Constants.READ_DATASOURCE_NAME);
        Object proceed = joinPoint.proceed();
        ThreadHolder.threadLocal.remove();
        return proceed;
    }

    @Around(value = "insertAroundService()")
    public Object insertAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        ThreadHolder.threadLocal.set(Constants.INSERT_DATASOURCE_NAME);
        Object proceed = joinPoint.proceed();
        ThreadHolder.threadLocal.remove();
        return proceed;
    }
}
