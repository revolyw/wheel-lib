package cn.willow.demo.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁的线程运行打印和锁定打印基本有序
 * @author willow
 * @since 2025/1/8
 */
public class FairDemo {
    public static class MyService{
        private ReentrantLock lock;
        public MyService(boolean isFair) {
            lock = new ReentrantLock(isFair);
        }
        public void serviceMethod(){
            try {
                lock.lock();
                System.out.println("ThreadName=" + Thread.currentThread().getName() + " 获得锁定");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final MyService service = new MyService(false);
        Runnable runnable = () -> {
            System.out.println("线程" + Thread.currentThread().getName() + "运行了");
            service.serviceMethod();
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }
}
