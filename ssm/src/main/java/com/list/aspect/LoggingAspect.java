package com.list.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/8 20:36
 */

@Component
@Aspect
public class LoggingAspect {

    @Around("execution(* *(..))")
    public Object aroundLogging(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        Object result = null;

        try {
            //前置通知
            System.out.println("QAQ*Logging: The method "+methodName+" begins with "+ Arrays.toString(args));
            //执行目标方法
            result = proceedingJoinPoint.proceed();
            //返回通知
            System.out.println("QAQ*Logging: The method "+methodName+" returns "+result);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("QAQ*Logging: The method "+methodName+" occurs "+throwable);
            throwable.printStackTrace();
        }finally {
            //后置通知
            System.out.println("QAQ*Logging: The method "+methodName+" ends");
        }
        return result;
    }

}

