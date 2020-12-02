package com.partise.ecommerceorderservice;

import com.partise.ecommerceorderservice.entity.ProductOrder;
import com.partise.ecommerceorderservice.service.ProductOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@ServletComponentScan
@EnableConfigurationProperties
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EcommerceOrderServiceApplication implements ApplicationRunner {

//    @Autowired
//    private ProductOrderMapper productOrderMapper;

	public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EcommerceOrderServiceApplication.class, args);
        ProductOrderService productOrderService = (ProductOrderService) applicationContext.getBean("productOrderService");
        ProductOrder productOrder = new ProductOrder(1L, 1L, new BigDecimal("20"), new BigDecimal("20"), "", 6, "", "2020-12-02", System.currentTimeMillis(), System.currentTimeMillis());
        productOrderService.insert(productOrder);

        List<ProductOrder> list = productOrderService.getProductOrderListByUserId(1L);
        log.info("list:{}", list);
	}

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        insert();
    }

    void insert(){
//        ProductOrder productOrder = new ProductOrder(1L, 1L, new BigDecimal("20"), new BigDecimal("20"), "", 6, "", "2020-12-02", System.currentTimeMillis(), System.currentTimeMillis());
//        productOrderMapper.insert(productOrder);
    }

}
