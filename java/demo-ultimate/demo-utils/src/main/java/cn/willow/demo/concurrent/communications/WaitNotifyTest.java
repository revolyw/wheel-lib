package cn.willow.demo.concurrent.communications;

import java.util.Vector;

/**
 * @author willow
 * @since 2024/12/26
 */
public class WaitNotifyTest {
    public static void main(String[] args) {
        Vector<Integer> pool = new Vector<>();
        Producer producer = new Producer(pool, 10);
        Consumer consumer = new Consumer(pool);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {
        private Vector<Integer> pool;
        private Integer size;

        public Producer(Vector<Integer> pool, Integer size) {
            this.pool = pool;
            this.size = size;
        }

        @Override
        public void run() {

        }
    }

    static class Consumer implements Runnable {
        private Vector<Integer> pool;

        public Consumer(Vector<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {

        }
    }
}
