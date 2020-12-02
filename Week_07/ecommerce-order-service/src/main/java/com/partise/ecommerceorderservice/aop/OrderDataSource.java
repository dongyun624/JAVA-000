package com.partise.ecommerceorderservice.aop;

import com.partise.ecommerceorderservice.constants.OrderConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OrderDataSource {


	String name() default OrderConstants.WRITE_DB_TYPE;
}
