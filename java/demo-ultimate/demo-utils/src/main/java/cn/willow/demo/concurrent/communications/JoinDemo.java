package cn.willow.demo.concurrent.communications;

import java.util.Date;

/**
 * @author willow
 * @since 2025/1/5
 */
public class JoinDemo {
    public static class ThreadA extends Thread {
        private ThreadB b;
        private boolean join;
        public ThreadA(ThreadB thread, boolean join) {
            this.b = thread;
            this.join = join;
        }

        @Override
        public void run() {
            synchronized (b) {
                b.start();
                try {
                    if (this.join) {
                        b.join(6000);
                    } else {
                        Thread.sleep(6000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static class ThreadB extends Thread {
        @Override
        public void run() {
            System.out.println("b run begin timer=" + new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("b run end timer=" + new Date());
        }

        synchronized public void bService(){
            System.out.println("print b service timer=" + new Date());
        }
    }
    public static class ThreadC extends Thread {
        private ThreadB threadB;
        public ThreadC(ThreadB target) {
            this.threadB = target;
        }

        @Override
        public void run() {
            threadB.bService();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        ThreadA a = new ThreadA(b, true);
        a.start();
        Thread.sleep(1000);
        ThreadC c = new ThreadC(b);
        c.start();
    }
}
