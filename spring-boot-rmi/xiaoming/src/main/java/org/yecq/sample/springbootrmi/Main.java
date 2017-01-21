package org.yecq.sample.springbootrmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yecq.sample.springbootrmi.controller.AllController;

/**
 * 入口
 *
 * @author yecq
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(AllController.class, args);
    }
}
