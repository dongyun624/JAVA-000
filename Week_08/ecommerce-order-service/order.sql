CREATE TABLE `product_order_0` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单主键id',
  `product_id` bigint NOT NULL COMMENT '商品id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `origin_price` decimal(11,6) NOT NULL COMMENT '订单原始价格',
  `purchase_price` decimal(11,6) NOT NULL COMMENT '订单实际价格',
  `order_msg` varchar(200) DEFAULT NULL COMMENT '订单信息',
  `status` int NOT NULL DEFAULT '0' COMMENT '订单状态，0:初始, 6:成功, 7:异常, 8:取消',
  `error_msg` varchar(200) DEFAULT NULL COMMENT '订单异常信息',
  `business_date` varchar(20) DEFAULT NULL COMMENT '订单发起日期，格式:yyyy-MM-dd, 对账用',
  `ctime` bigint DEFAULT NULL COMMENT '创建时间, 单位:毫秒',
  `utime` bigint DEFAULT NULL COMMENT '更新时间, 单位:毫秒',
  PRIMARY KEY (`order_id`),
  KEY `idx_userId` (`user_id`),
  KEY `idx_productId` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单表';
CREATE TABLE `product_order_1` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单主键id',
  `product_id` bigint NOT NULL COMMENT '商品id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `origin_price` decimal(11,6) NOT NULL COMMENT '订单原始价格',
  `purchase_price` decimal(11,6) NOT NULL COMMENT '订单实际价格',
  `order_msg` varchar(200) DEFAULT NULL COMMENT '订单信息',
  `status` int NOT NULL DEFAULT '0' COMMENT '订单状态，0:初始, 6:成功, 7:异常, 8:取消',
  `error_msg` varchar(200) DEFAULT NULL COMMENT '订单异常信息',
  `business_date` varchar(20) DEFAULT NULL COMMENT '订单发起日期，格式:yyyy-MM-dd, 对账用',
  `ctime` bigint DEFAULT NULL COMMENT '创建时间, 单位:毫秒',
  `utime` bigint DEFAULT NULL COMMENT '更新时间, 单位:毫秒',
  PRIMARY KEY (`order_id`),
  KEY `idx_userId` (`user_id`),
  KEY `idx_productId` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单表';
CREATE TABLE `product_order_2` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单主键id',
  `product_id` bigint NOT NULL COMMENT '商品id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `origin_price` decimal(11,6) NOT NULL COMMENT '订单原始价格',
  `purchase_price` decimal(11,6) NOT NULL COMMENT '订单实际价格',
  `order_msg` varchar(200) DEFAULT NULL COMMENT '订单信息',
  `status` int NOT NULL DEFAULT '0' COMMENT '订单状态，0:初始, 6:成功, 7:异常, 8:取消',
  `error_msg` varchar(200) DEFAULT NULL COMMENT '订单异常信息',
  `business_date` varchar(20) DEFAULT NULL COMMENT '订单发起日期，格式:yyyy-MM-dd, 对账用',
  `ctime` bigint DEFAULT NULL COMMENT '创建时间, 单位:毫秒',
  `utime` bigint DEFAULT NULL COMMENT '更新时间, 单位:毫秒',
  PRIMARY KEY (`order_id`),
  KEY `idx_userId` (`user_id`),
  KEY `idx_productId` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单表';