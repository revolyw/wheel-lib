package cn.willowspace.oldwang;

import cn.willowspace.oldwang.controller.AppController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Willow on 1/22/17.
 */
@SpringBootApplication
public class OldWangApp {
    public static void main(String[] args) {
        SpringApplication.run(AppController.class);
    }

}
