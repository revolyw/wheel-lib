package cn.willow.demo.concurrent.synchronizers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author willow
 * @since 2024/12/10
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    boolean permit = semaphore.tryAcquire(1, 6, TimeUnit.SECONDS);
                    if (permit) {
                        System.out.println("hello " + finalI);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
            thread.start();
        }
    }
}
