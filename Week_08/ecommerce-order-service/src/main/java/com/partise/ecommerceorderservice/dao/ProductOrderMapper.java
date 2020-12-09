package com.partise.ecommerceorderservice.dao;

import com.partise.ecommerceorderservice.entity.ProductOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductOrderMapper {

    void insert(ProductOrder productOrder);

    ProductOrder getProductOrder(@Param("orderId") Integer orderId);

    List<ProductOrder> getProductOrderListByUserId(@Param("userId") Long userId);

}