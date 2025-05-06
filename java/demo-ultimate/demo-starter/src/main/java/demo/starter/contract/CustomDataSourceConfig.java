package demo.starter.contract;

import demo.starter.CustomDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author willow
 * @since 2024/12/17
 */
@Configuration
public class CustomDataSourceConfig {
    @Bean
    public CustomDataSource datasource() {
        return new CustomDataSource();
    }
}
