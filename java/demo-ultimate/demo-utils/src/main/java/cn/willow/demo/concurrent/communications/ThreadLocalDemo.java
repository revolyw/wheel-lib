package cn.willow.demo.concurrent.communications;

import java.util.Date;

/**
 * @author willow
 * @since 2025/1/7
 */
public class ThreadLocalDemo {
    public static class ThreadLocalExt extends ThreadLocal<Date> {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    }
    public static class Tool {
        public static ThreadLocalExt t1 = new ThreadLocalExt();
    }

    public static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("在 ThreadA 中取值=" + Tool.t1.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("在 Main 中的取值=" + Tool.t1.get());
            Thread.sleep(100);
        }
        Thread.sleep(5000);
        ThreadA a = new ThreadA();
        a.start();
    }
}
