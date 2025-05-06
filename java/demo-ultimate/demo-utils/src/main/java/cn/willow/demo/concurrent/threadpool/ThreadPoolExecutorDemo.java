package cn.willow.demo.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * @author willow
 * @since 2024/12/10
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadFactory factory = (r) -> new Thread(r, "my-thread");
        RejectedExecutionHandler handler = (r, executor) -> {
            throw new RuntimeException("rejected");
        };
        ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1, 2,
                10L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), factory, handler);
        Runnable task = () -> System.out.println("hello world");
        EXECUTOR.execute(task);
    }
}
