### 秒杀系统设计
1、前端页面展示时动静资源分离，避免静态内容重复加载   
2、添加缓存，避免DB压力过大，缓存商品不经常变动的信息及库存信息等   
3、缓存减库存时避免超卖，最终也可以通过DB控制唯一性（唯一流水号）    
4、限制刷单，显示每个账户或ip购买最大数量  
5、下订单添加异步处理，订单是否真正成功通过异步的DB结果确认， 或者 对下订单时对用户加分布式锁，避免重复下订单    
6、可以通过Semaphore或RateLimiter对订单查询接口或下单接口添加限流    
7、保证核心服务正常，当服务器压力过大时，对秒杀设计的服务做限流、降级、熔断处理     