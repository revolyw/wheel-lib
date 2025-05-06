package cn.willow.demo.concurrent.container;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author willow
 * @since 2024/12/10
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        System.out.println(list.size());
    }
}
