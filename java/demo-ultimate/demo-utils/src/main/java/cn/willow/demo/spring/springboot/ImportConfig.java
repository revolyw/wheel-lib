package cn.willow.demo.spring.springboot;

import demo.starter.CustomDataSource;
import demo.starter.contract.CustomDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 演示如何从二方或三方包中集成、重写或组织配置
 * @author willow
 * @since 2024/12/17
 */
@Configuration
@Import(CustomDataSourceConfig.class)
public class ImportConfig {
    @Autowired
    private CustomDataSource datasource;
    @Bean
    public CustomDataSource myDatasource() {
        //重写配置
        datasource.setHost("localhost");
        datasource.setPort("3306");
        return datasource;
    }
}
