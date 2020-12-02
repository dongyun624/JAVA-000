package com.partise.ecommerceorderservice.service;

import com.partise.ecommerceorderservice.entity.ProductOrder;

import java.util.List;

/**
 * @author : dabing
 * @date : 2020/12/2
 */
public interface ProductOrderService {

    void insert(ProductOrder productOrder);

    ProductOrder getProductOrder(Integer orderId);

    List<ProductOrder> getProductOrderListByUserId(Long userId);

}
