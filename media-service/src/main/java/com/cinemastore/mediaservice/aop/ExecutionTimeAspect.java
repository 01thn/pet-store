package com.cinemastore.mediaservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    private final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceClass() {
    }

    @Around("isServiceClass()")
    public Object measureExecution(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Class Name: "
                + point.getSignature().getDeclaringTypeName() +
                ". Method Name: " + point.getSignature().getName() +
                ". Time taken for Execution: " + (endTime - startTime) + "ms");
        return object;
    }
}
