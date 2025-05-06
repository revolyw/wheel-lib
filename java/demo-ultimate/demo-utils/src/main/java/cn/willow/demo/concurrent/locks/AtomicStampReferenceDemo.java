package cn.willow.demo.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author willow
 * @since 2024/12/10
 */
public class AtomicStampReferenceDemo {
    private static AtomicStampedReference<String> stampedReference
            = new AtomicStampedReference<>("initial", 0);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            //获取当前引用和 stamp
            int[] stamp = new int[1];
            String expectedReference = stampedReference.get(stamp);
            System.out.println("thread1 current reference:" + expectedReference + " stamp:" + stamp[0]);

            //尝试更新引用，如果成功，stamp 也会更新
            String newReference = "value1";
            boolean updated = stampedReference.compareAndSet(expectedReference, newReference, stamp[0], stamp[0] + 1);
            if (updated) {
                System.out.println("thread1 update successfully reference to " + newReference);
            } else {
                System.out.println("thread1 update failed");
            }
        });
        //如果第二个线程不与第一个线程同时提交并发，则结果可能不一样
//        Thread.sleep(1000);
        service.submit(()->{
            int[] stamp = new int[1];
            String expectedReference = stampedReference.get(stamp);
            System.out.println("thread2 current reference:" + expectedReference + " stamp:" + stamp[0]);

            String newReference = "initial";
            boolean updated = stampedReference.compareAndSet(expectedReference, newReference, stamp[0], stamp[0] + 1);
            if (updated) {
                System.out.println("thread2 update successfully reference to " + newReference);
            } else {
                System.out.println("thread2 update failed");
            }
        });
    }
}
