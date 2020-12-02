package com.partise.ecommerceorderservice.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.partise.ecommerceorderservice.constants.OrderConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pqhu on 2017/9/6.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
    public static final String PACKAGE = "com.partise.ecommerceorderservice.dao";
    public static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";
    public static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";

    @Bean(name = "writeDataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.order.w")
    public DruidDataSource writeDataSource() {
        return (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "readDataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.order.r")
    public DruidDataSource readDataSource() {
        return (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "operation.dynamic.ds")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(writeDataSource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(2);
        dsMap.put(OrderConstants.WRITE_DB_TYPE, writeDataSource());
        dsMap.put(OrderConstants.READ_DB_TYPE, readDataSource());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource());
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource(DataSourceConfig.CONFIG_LOCATION));
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.MAPPER_LOCATION));

        return factoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
