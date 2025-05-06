package cn.willow.demo.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author willow
 * @date 2024/7/3
 */
public class ArrayBlockingQueueUtil {
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(20000);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }
}
