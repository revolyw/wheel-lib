package cn.willow.demo.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoGuavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGuavaApplication.class, args);
        SetDemo.demo();
    }

}
