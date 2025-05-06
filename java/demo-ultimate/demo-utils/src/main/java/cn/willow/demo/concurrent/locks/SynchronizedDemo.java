package cn.willow.demo.concurrent.locks;

/**
 * @author willow
 * @since 2024/12/10
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        //静态同步方法和同步代码块按类对象同步
        Thread staticSyncTread = new Thread(SynchronizedDemo::staticSyncMethod);
        staticSyncTread.start();
        synchronized (SynchronizedDemo.class){
            System.out.println("synchronized block with SynchronizedDemo class");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //成员同步方法与同步代码块按对象实例同步
        SynchronizedDemo demo = new SynchronizedDemo();
        Thread instanceSyncTread = new Thread(demo::instanceSyncMethod);
        instanceSyncTread.start();
        synchronized (demo){
            System.out.println("synchronized block with SynchronizedDemo instance");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static synchronized void staticSyncMethod() {
        System.out.println("staticSyncMethod");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void instanceSyncMethod() {
        System.out.println("instanceSyncMethod");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
