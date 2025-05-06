package cn.willow.demo.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author willow
 * @since 2025/1/7
 */
public class ConditionWaitNotifyDemo {
    public static class MyService {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void await() {
            try {
                lock.lock();
                System.out.println("await 时间为：" + System.currentTimeMillis());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("锁释放了");
            }
        }

        public void signal(){
            try {
                lock.lock();
                System.out.println("signal 时间为：" + System.currentTimeMillis());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadA extends Thread{
        private MyService service;

        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.await();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        Thread.sleep(3000);
        service.signal();
    }
}
