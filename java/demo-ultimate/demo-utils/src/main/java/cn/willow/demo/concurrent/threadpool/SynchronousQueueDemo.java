package cn.willow.demo.concurrent.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author willow
 * @since 2024/12/9
 */
public class SynchronousQueueDemo {
    public static final SynchronousQueue<String> queue = new SynchronousQueue<>();
    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            //case offer
//            boolean produce = queue.offer("hello");
//            System.out.println("produce result:" + produce);
            //case put
//            try {
//                queue.put("hello");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            //case add
            boolean produce = queue.add("hello");
            System.out.println("produce result:" + produce);
        });
        Thread consumer = new Thread(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            String poll = null;
            try {
                poll = queue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("consume:" + poll);
        });
        consumer.start();
        producer.start();
    }
}
