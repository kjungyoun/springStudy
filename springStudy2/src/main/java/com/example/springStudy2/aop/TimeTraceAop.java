package com.example.springStudy2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* com.example.springStudy2..*(..))") // 이 메소드가 적용되는 범위를 지정해줄수 있다. *(..) 이 의미는 springStudy2 아래에 있는 모든 패키지 내부의 모든 클래스를 의미
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("Finish ( "+ joinPoint.getClass()+ "):" +timeMs + "ms");
        }
    }
}
