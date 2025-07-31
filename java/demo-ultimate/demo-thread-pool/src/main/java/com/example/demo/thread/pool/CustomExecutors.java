package com.example.demo.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author willow
 * @date 2022/12/29
 */
public class CustomExecutors {
    /**
     * 拒绝策略
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = (runnable, executor) ->
            System.out.println("rejected. " + Thread.currentThread().getName()
                    + ThreadPoolExecutorAnalyzer.getExecutorInfo(executor));

    /**
     * 单线程
     * 无界队列
     *
     * @return
     */
    public static ThreadPoolExecutor newSingleThreadPoolExecutor() {
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                REJECTED_EXECUTION_HANDLER
        );
    }

    public static ThreadPoolExecutor newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                REJECTED_EXECUTION_HANDLER
        );
    }
}
