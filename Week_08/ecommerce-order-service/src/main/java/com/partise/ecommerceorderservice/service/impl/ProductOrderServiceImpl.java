package com.partise.ecommerceorderservice.service.impl;

import com.partise.ecommerceorderservice.dao.ProductOrderMapper;
import com.partise.ecommerceorderservice.entity.ProductOrder;
import com.partise.ecommerceorderservice.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : dabing
 * @date : 2020/12/2
 */
@Service("productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Override
    public void insert(ProductOrder productOrder) {
        productOrderMapper.insert(productOrder);
    }

    @Override
    public ProductOrder getProductOrder(Integer orderId) {
        return productOrderMapper.getProductOrder(orderId);
    }

    @Override
    public List<ProductOrder> getProductOrderListByUserId(Long userId) {
        return productOrderMapper.getProductOrderListByUserId(userId);
    }
}
