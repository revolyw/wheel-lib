package com.example.demo.thread.pool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author willow
 * @date 2022/12/29
 */
public class ThreadPoolExecutorAnalyzer {
    public static String getExecutorInfo(ThreadPoolExecutor executor) {
        long taskCount = executor.getTaskCount();
        long completedTaskCount = executor.getCompletedTaskCount();
        // 队列等待中的任务数量
        int queueTaskCount = executor.getQueue().size();
        // 队列剩余容量
        int queueRemainingCapacity = executor.getQueue().remainingCapacity();
        // 线程执行中的任务数
        int activeCount = executor.getActiveCount();
        //核心线程数
        int corePoolSize = executor.getCorePoolSize();
        // 最大线程数
        int maximumPoolSize = executor.getMaximumPoolSize();
        // 线程池最大负载任务数，超过就会执行拒绝策略
        long maxLoadTaskCount = maximumPoolSize + queueRemainingCapacity + queueTaskCount;
        // 线程池当前负载任务数
        long currentLoadTaskCount = activeCount + queueTaskCount;
        // 线程池当前剩余负载
        long remainingLoadTaskCount = maxLoadTaskCount - currentLoadTaskCount;
        // 剩余待处理的任务
        long needExecuteTaskCount = taskCount - completedTaskCount - currentLoadTaskCount;
        // 需要拒绝的任务
        long needRejectedTaskCount = needExecuteTaskCount >= remainingLoadTaskCount ?
                needExecuteTaskCount - remainingLoadTaskCount + 1 : 0;
        return " "
                + "执行任务的线程数:" + activeCount + " "
                + "队列中的任务数:" + queueTaskCount + " "
                + "执行完的任务数:" + completedTaskCount + " "
                + "任务总数:" + taskCount + " "
//                + "线程池当前剩余负载:" + remainingLoadTaskCount + " "
//                + "剩余待处理的任务:" + needExecuteTaskCount + " "
                + (activeCount >= maximumPoolSize ? "线程满负载" : "") + " "
                + ((activeCount >= corePoolSize && activeCount <= maximumPoolSize) && queueRemainingCapacity > 0 ? "入队等待" : "") + " "
                + (queueRemainingCapacity <= 0 ? "队列已满" : "") + " "
                + (needRejectedTaskCount > 0 ? "需要拒绝的任务:" + needRejectedTaskCount + " " : "")
                + "\n";
    }

}
