package cn.willow.demo.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author willow
 * @since 2024/12/9
 */
public class FixedThreadPoolDemo {
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(1);
    public static void main(String[] args) {
        SERVICE.submit(() ->{

        });
    }
}
