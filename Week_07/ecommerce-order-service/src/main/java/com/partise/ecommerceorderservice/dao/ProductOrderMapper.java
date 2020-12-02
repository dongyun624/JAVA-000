package com.partise.ecommerceorderservice.dao;

import com.partise.ecommerceorderservice.entity.ProductOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderMapper {

    void insert(ProductOrder productOrder);

//    @OrderDataSource(name = OrderConstants.READ_DB_TYPE)
    ProductOrder getProductOrder(@Param("orderId") Integer orderId);

//    @OrderDataSource(name = OrderConstants.READ_DB_TYPE)
    List<ProductOrder> getProductOrderListByUserId(@Param("userId") Long userId);

}