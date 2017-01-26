package cn.willowspace.greenqiang;

import cn.willowspace.greenqiang.controller.AppController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Willow on 1/22/17.
 */
@SpringBootApplication
public class GreenQiangApp {
    public static void main(String[] args) {
        SpringApplication.run(AppController.class);
    }
}
