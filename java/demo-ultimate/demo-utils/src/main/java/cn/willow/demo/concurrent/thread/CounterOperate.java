package cn.willow.demo.concurrent.thread;

/**
 * @author willow
 * @since 2024/12/30
 */
public class CounterOperate extends Thread {
    public CounterOperate() {
        System.out.println("CountOperate--begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().getAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.getAlive()=" + this.isAlive());
        System.out.println("CountOperate--end");
    }

    @Override
    public void run() {
        System.out.println("run--begin");
        //Thread.currentThread().getClass()=class java.lang.Thread
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().getAlive()=" + Thread.currentThread().isAlive());
        //this.getClass()=class cn.willow.demo.thread.CounterOperate
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.getAlive()=" + this.isAlive());
        System.out.println("run--end");
    }

    public static void main(String[] args) {
        CounterOperate c = new CounterOperate();
        Thread t1 = new Thread(c);
        System.out.println("main begin t1 isAlive=" + t1.isAlive());
        t1.setName("A");
        /** {@link Thread#run()} */
        t1.start();
        System.out.println("main end t1 isAlive=" + t1.isAlive());
    }
}
