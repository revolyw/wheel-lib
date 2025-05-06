package cn.willow.demo.concurrent.future;

import java.util.concurrent.*;

/**
 * @author willow
 * @since 2024/12/10
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("hello world");
            return 0;
        });
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(task);
        try {
            //查询任务状态
            System.out.println("task isDone:" + task.isDone());
//            //尝试取消任务，中断正在执行的任务
//            boolean cancel = task.cancel(true);
//            if (cancel) {
//                System.out.println("task canceled");
//            } else {
//                System.out.println("task cancel failed");
//            }
//            System.out.println("task isDone:" + task.isDone());

            Integer result = task.get();
            System.out.println("result:" + result);
            System.out.println("task isDone:" + task.isDone());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
