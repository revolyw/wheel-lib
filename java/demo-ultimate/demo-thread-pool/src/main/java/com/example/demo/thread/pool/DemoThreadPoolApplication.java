package com.example.demo.thread.pool;


import java.util.concurrent.ThreadPoolExecutor;

public class DemoThreadPoolApplication {
    public static void main(String[] args) {
//        Executors.newFixedThreadPool();
//        ThreadPoolExecutor singleThreadPoolExecutor = CustomExecutors.newSingleThreadPoolExecutor();
//        submitTasks(singleThreadPoolExecutor);

        ThreadPoolExecutor fixedThreadPool = CustomExecutors.newFixedThreadPool(2);
        submitTasks(fixedThreadPool);
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String taskName = "task[" + (i + 1) + "]";
            executor.execute(() -> {
                System.out.println(taskName + " executing... " + Thread.currentThread().getName() + " "
                        + ThreadPoolExecutorAnalyzer.getExecutorInfo(executor));
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
