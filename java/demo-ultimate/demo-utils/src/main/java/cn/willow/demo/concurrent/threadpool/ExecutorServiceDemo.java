package cn.willow.demo.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author willow
 * @since 2024/12/9
 */
public class ExecutorServiceDemo {
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(1);
    public static void main(String[] args) throws InterruptedException {
        //以 runnable 提交任务
        SERVICE.submit(() -> {
            System.out.println("hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("world");
        });
        //立即关闭线程池
//        SERVICE.shutdownNow();
//        System.out.println("shutdown state:" + SERVICE.isShutdown());
        //等待任务执行完后关闭线程池
        SERVICE.shutdown();
        System.out.println("shutdown state:" + SERVICE.isShutdown());
        //等待任务执行完成
        boolean result = SERVICE.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("task result:" + result + " shutdown state:" + SERVICE.isShutdown());
        //没有任务时关闭线程池
        SERVICE.shutdown();
        System.out.println("shutdown state:" + SERVICE.isShutdown());
    }
}
