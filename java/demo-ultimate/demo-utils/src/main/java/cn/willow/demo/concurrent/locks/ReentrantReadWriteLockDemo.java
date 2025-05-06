package cn.willow.demo.concurrent.locks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author willow
 * @since 2024/12/10
 */
public class ReentrantReadWriteLockDemo {
    private double x,y;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readlock = lock.readLock();
    private Lock writelock = lock.writeLock();
    public double read() {
        readlock.lock();
        try {
            return Math.sqrt(x * x + y * y);
        } finally {
            readlock.unlock();
        }
    }

    public void move(double deltaX, double deltaY) {
        writelock.lock();
        try {
            x += deltaX;
            y += deltaY;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            writelock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        Runnable taskSqrt = () -> {
            double sqrt = demo.read();
            System.out.println("x:" + demo.x + "y:" + demo.y + "sqrt:" + sqrt);
        };
        Runnable taskMove = () -> {
            System.out.println("move");
            demo.move(1D, 1D);
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleAtFixedRate(taskSqrt, 0, 500, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(taskSqrt, 0, 500, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(taskMove, 0, 500, TimeUnit.MILLISECONDS);
    }
}
