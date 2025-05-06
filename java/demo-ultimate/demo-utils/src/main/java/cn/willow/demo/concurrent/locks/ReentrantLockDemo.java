package cn.willow.demo.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author willow
 * @since 2025/1/7
 */
public class ReentrantLockDemo {
    public static class MyService{
        private Lock lock = new ReentrantLock();

        public void methodA() {
            try {
                lock.lock();
                System.out.println("methodA begin ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println("methodA end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void methodB() {
            try {
                lock.lock();
                System.out.println("methodB begin ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println("methodB end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadA extends Thread {
        private MyService service;

        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodA();
        }
    }

    public static class ThreadAA extends Thread {
        private MyService service;

        public ThreadAA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodA();
        }
    }

    public static class ThreadB extends Thread {
        private MyService service;

        public ThreadB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodB();
        }
    }

    public static class ThreadBB extends Thread {
        private MyService service;

        public ThreadBB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodB();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadA aa = new ThreadA(service);
        aa.setName("AA");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        ThreadBB bb = new ThreadBB(service);
        bb.setName("BB");
        a.start();
        aa.start();
        Thread.sleep(100);
        b.start();
        bb.start();
    }
}
