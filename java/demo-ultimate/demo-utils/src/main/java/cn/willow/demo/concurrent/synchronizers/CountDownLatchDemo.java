package cn.willow.demo.concurrent.synchronizers;

import java.util.concurrent.CountDownLatch;

/**
 * @author willow
 * @since 2024/12/10
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("world");
        }).start();
        new Thread(() -> {
            System.out.println("hello");
            latch.countDown();
        }).start();
    }
}
