package cn.willow.demo.concurrent.threadpool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author willow
 * @since 2024/12/10
 */
public class WorkStealingThreadPoolDemo {
    public static final ExecutorService SERVICE = Executors.newWorkStealingPool(4);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("hello:" + new Date());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        for (int i = 0; i < 100; i++) {
            Future<?> submit = SERVICE.submit(task);
//            try {
//                submit.get(100, java.util.concurrent.TimeUnit.MILLISECONDS);
//            } catch (InterruptedException | ExecutionException | TimeoutException e) {
//                throw new RuntimeException(e);
//            }
        }
        Thread.sleep(100000);
    }
}
