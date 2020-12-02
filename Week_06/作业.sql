CREATE TABLE `user` (
  `user_id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nick_name` varchar(50) NOT NULL COMMENT '昵称',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `entry_password` varchar(200) NOT NULL COMMENT '加密密码',
  `mask_mobile` varchar(20) DEFAULT NULL COMMENT 'mask手机号',
  `entry_mobile` varchar(300) DEFAULT NULL COMMENT '加密的手机号',
  `mask_id_card` varchar(20) DEFAULT NULL COMMENT 'mask身份证号',
  `entry_id_card` varchar(300) DEFAULT NULL COMMENT '加密的身份证号',
  `mask_email` varchar(20) DEFAULT NULL COMMENT 'mask邮箱',
  `entry_email` varchar(300) DEFAULT NULL COMMENT '加密邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '用户地址',
  `ctime` bigint(21) DEFAULT NULL COMMENT '创建时间, 单位:毫秒',
  `utime` bigint(21) DEFAULT NULL COMMENT '更新时间, 单位:毫秒',
  PRIMARY KEY (`user_id`),
  KEY `idx_nickName` (`nick_name`),
  KEY `idx_entryMobile` (`entry_mobile`),
  KEY `idx_entryIdCard` (`entry_id_card`),
  KEY `idx_entryEmail` (`entry_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `product` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(11,6) NOT NULL COMMENT '商品价格',
  `product_desc` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `picture` varchar(200) DEFAULT NULL COMMENT '商品图片地址',
  `ctime` bigint(21) DEFAULT NULL COMMENT '创建时间, 单位:毫秒',
  `utime` bigint(21) DEFAULT NULL COMMENT '更新时间, 单位:毫秒',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

CREATE TABLE `product_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键id',
  `product_id` bigint(21) NOT NULL COMMENT '商品id',
  `user_id` bigint(21) NOT NULL COMMENT '用户id',
  `origin_price` decimal(11,6) NOT NULL COMMENT '订单原始价格',
  `purchase_price` decimal(11,6) NOT NULL COMMENT '订单实际价格',
  `order_msg` varchar(200) DEFAULT NULL COMMENT '订单信息',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态，0:初始, 6:成功, 7:异常, 8:取消',
  `error_msg` varchar(200) DEFAULT NULL COMMENT '订单异常信息',
  `business_date` varchar(20) DEFAULT NULL COMMENT '订单发起日期，格式:yyyy-MM-dd, 对账用',
  `ctime` bigint(21) DEFAULT NULL COMMENT '创建时间, 单位:毫秒',
  `utime` bigint(21) DEFAULT NULL COMMENT '更新时间, 单位:毫秒',
  PRIMARY KEY (`order_id`),
  KEY `idx_userId` (`user_id`),
  KEY `idx_productId` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单表';
