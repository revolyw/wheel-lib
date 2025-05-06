package cn.willow.demo.concurrent.locks;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author willow
 * @since 2024/12/10
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.getAndIncrement());
    }
}
