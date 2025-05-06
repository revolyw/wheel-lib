package cn.willow.demo.concurrent.synchronizers;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author willow
 * @since 2025/3/24
 */
public class PhaserDemo {
    public static void main(String[] args) {
        // 创建一个 Phaser 实例
        Phaser phaser = new Phaser(1); // 初始注册一个 party（主线程）

        // 创建并启动多个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Task(phaser), "Thread-" + i).start();
            phaser.register(); // 每创建一个线程就注册一个 party
        }

        // 主线程执行一些操作
        System.out.println("主线程开始执行任务...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("主线程任务完成，到达屏障点1");

        // 主线程到达屏障点1
        phaser.arriveAndAwaitAdvance();

        // 主线程执行一些操作
        System.out.println("主线程开始执行任务...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("主线程任务完成，到达屏障点2");

        // 主线程到达屏障点2
        phaser.arriveAndAwaitAdvance();

        // 主线程执行一些操作
        System.out.println("主线程开始执行任务...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("主线程任务完成，到达屏障点3");

        // 主线程到达屏障点3
        phaser.arriveAndAwaitAdvance();

        // 主线程取消注册
        phaser.arriveAndDeregister();
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始执行任务...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " 任务完成，到达屏障点1");

            // 线程到达屏障点1
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " 开始执行任务...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " 任务完成，到达屏障点2");

            // 线程到达屏障点2
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " 开始执行任务...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " 任务完成，到达屏障点3");

            // 线程到达屏障点3
            phaser.arriveAndAwaitAdvance();

            // 线程取消注册
            phaser.arriveAndDeregister();
        }
    }
}
