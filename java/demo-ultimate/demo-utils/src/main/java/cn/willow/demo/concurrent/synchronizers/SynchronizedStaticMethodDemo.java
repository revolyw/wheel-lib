package cn.willow.demo.concurrent.synchronizers;

/**
 * @author willow
 * @since 2025/1/1
 */
public class SynchronizedStaticMethodDemo implements Runnable{
    private boolean m1;
    public SynchronizedStaticMethodDemo(boolean m1) {
        this.m1 = m1;
    }

    public static void main(String[] args) {
        SynchronizedStaticMethodDemo s1 = new SynchronizedStaticMethodDemo(true);
        Thread t1 = new Thread(s1);
        t1.start();
        SynchronizedStaticMethodDemo s2 = new SynchronizedStaticMethodDemo(false);
        Thread t2 = new Thread(s2);
        t2.start();
    }
    public synchronized static void m1() throws InterruptedException {
        System.out.println("m1 begin");
        Thread.sleep(5000);
        System.out.println("m1 end");
    }

    public synchronized static void m2() throws InterruptedException {
        System.out.println("m2 begin");
        Thread.sleep(5000);
        System.out.println("m2 end");
    }

    @Override
    public void run() {
        if (m1) {
            try {
                m1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                m2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
