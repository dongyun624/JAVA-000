package com.partise.ecommerceorderservice.aspect;

import com.partise.ecommerceorderservice.aop.OrderDataSource;
import com.partise.ecommerceorderservice.config.DatabaseContextHolder;
import com.partise.ecommerceorderservice.constants.OrderConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class DataSourceRouter {

    @Around("@annotation(com.partise.ecommerceorderservice.aop.OrderDataSource)")
    public Object dataSourceRouter(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        OrderDataSource annotation = methodSignature.getMethod().getAnnotation(OrderDataSource.class);

        try{
            String dataSourceName = annotation.name();
            if(StringUtils.hasText(dataSourceName)){
                DatabaseContextHolder.setDBKey(dataSourceName);
            } else{
                DatabaseContextHolder.setDBKey(OrderConstants.READ_DB_TYPE);
            }
            return joinPoint.proceed();
        }finally {
            DatabaseContextHolder.clearDBKey();
        }
    }


}
