package cn.willow.demo;

import demo.starter.contract.StartRateLimiter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 说明：@ComponentScan("another.pkg") 见 {@link another.pkg.ComponentScanTestController}
 * @author willow
 * @since 2024/12/17
 */
@ComponentScan({"another.pkg", "cn.willow.demo"})
@SpringBootApplication(exclude = {/*CustomAutoConfiguration.class*/}, scanBasePackages = "basePackages")
public class Application implements ApplicationRunner {
    /**
     * 演示基于条件注解的自动配置。自动配置位于起步依赖 demo-starter 中
     * @return 条件实例
     */
    @Bean
    public StartRateLimiter startRateLimiter() {
        return new StartRateLimiter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("do something after application initialized");
    }
}
