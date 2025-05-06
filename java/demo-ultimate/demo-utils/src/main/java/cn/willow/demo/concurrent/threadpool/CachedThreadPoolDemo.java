package cn.willow.demo.concurrent.threadpool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author willow
 * @since 2024/12/9
 */
public class CachedThreadPoolDemo {
    public static final ExecutorService SERVICE = Executors.newCachedThreadPool();
    public static void main(String[] args) throws InterruptedException {
        SERVICE.submit(() -> {
            System.out.print(new Date() + ":");
            System.out.print("hell world ");
            System.out.println(Thread.currentThread().getName());
        });
//        Thread.sleep(100);
        Thread.sleep(59900);
        Thread.sleep(60100);
        SERVICE.submit(() -> {
            System.out.print(new Date() + ":");
            System.out.print("hell world ");
            System.out.println(Thread.currentThread().getName());
        });
    }
}
