package cn.willow.demo.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author willow
 * @since 2024/12/10
 */
public class ThreadFactoryDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("my-thread");
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        };
        ExecutorService executor = Executors.newFixedThreadPool(3, threadFactory);
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " 正在执行任务");
        });
        Thread.sleep(1000);
        executor.shutdown();
    }
}
