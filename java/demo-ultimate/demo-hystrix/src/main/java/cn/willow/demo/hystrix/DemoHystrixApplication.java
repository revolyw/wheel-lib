package cn.willow.demo.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class DemoHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHystrixApplication.class, args);
    }

}
