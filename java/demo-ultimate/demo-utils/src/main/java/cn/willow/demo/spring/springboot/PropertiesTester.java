package cn.willow.demo.spring.springboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 查看不同配置的加载顺序
 *
 * @author willow
 * @since 2024/12/17
 */
@Configuration
@PropertySource("")
public class PropertiesTester implements ApplicationContextAware {
    @Value("${custom.name}")
    private String customName;
    @Value("${custom.self.application.name}")
    private String selfApplicationName;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("custom.name:" + customName);
        System.out.println("custom.self.application.name:" + selfApplicationName);
    }
}
