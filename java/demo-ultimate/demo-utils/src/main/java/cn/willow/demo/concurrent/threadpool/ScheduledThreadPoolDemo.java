package cn.willow.demo.concurrent.threadpool;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author willow
 * @since 2024/12/10
 */
public class ScheduledThreadPoolDemo {
    public static final ScheduledExecutorService SERVICE = Executors.newScheduledThreadPool(2);
    public static final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start:" + new Date());
        Runnable task = () -> {
            System.out.println("hello " + counter.getAndIncrement() + ":" + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        //延迟执行任务
//        SERVICE.schedule(task, 1000, TimeUnit.MILLISECONDS);
//        Thread.sleep(1000);
        //定时执行任务（不论任务本身执行多久，固定间隔发起执行
//        SERVICE.scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
//        monitorThreadPoolExecutor(SERVICE, 20);
        //固定延迟执行任务（上一次任务执行完后，延迟固定间隔才发起执行
//        SERVICE.scheduleWithFixedDelay(task, 0, 1000, TimeUnit.MILLISECONDS);
//        monitorThreadPoolExecutor(SERVICE, 10);
    }

    private static void monitorThreadPoolExecutor(ExecutorService executorService, int seconds) {
        String lastInfo = "";
        while (true) {
            if(counter.get() > 10000) {
                break;
            }
            if (executorService instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) SERVICE;
                String info = " pool size:" + threadPoolExecutor.getLargestPoolSize()
                        + " task count " + threadPoolExecutor.getTaskCount()
                        + " completed:" + threadPoolExecutor.getCompletedTaskCount()
                        + " queue count:" + threadPoolExecutor.getQueue().size();
                if (Objects.equals(lastInfo, info)) {
                    continue;
                }
                System.out.println(info);
                lastInfo = info;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
