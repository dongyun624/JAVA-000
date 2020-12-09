package com.partise.ecommerceorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author : dabing
 * @date : 2020/12/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductOrder {

    /**
     * 订单主键id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单原始价格
     */
    private BigDecimal originPrice;

    /**
     * 订单实际价格
     */
    private BigDecimal purchasePrice;

    /**
     * 订单信息
     */
    private String orderMsg;

    /**
     * 订单状态，0:初始, 6:成功, 7:异常, 8:取消
     */
    private Integer status;

    /**
     * 订单异常信息
     */
    private String errorMsg;

    /**
     * 订单发起日期，格式:yyyy-MM-dd, 对账用
     */
    private String businessDate;

    /**
     * 创建时间, 单位:毫秒
     */
    private Long ctime;

    /**
     * 更新时间, 单位:毫秒
     */
    private Long utime;

    public ProductOrder(Long productId, Long userId, BigDecimal originPrice, BigDecimal purchasePrice, String orderMsg, Integer status, String errorMsg,
                        String businessDate, Long ctime, Long utime) {
        this.productId = productId;
        this.userId = userId;
        this.originPrice = originPrice;
        this.purchasePrice = purchasePrice;
        this.orderMsg = orderMsg;
        this.status = status;
        this.errorMsg = errorMsg;
        this.businessDate = businessDate;
        this.ctime = ctime;
        this.utime = utime;
    }
}
