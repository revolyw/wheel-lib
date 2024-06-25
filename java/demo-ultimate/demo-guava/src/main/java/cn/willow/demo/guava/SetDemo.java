package cn.willow.demo.guava;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * 集合实例
 * @author willow
 * @date 2024/6/25
 */
public class SetDemo {
    public static void demo() {
        Set<String> actual = new HashSet<>();
        actual.add("1");
        actual.add("2");
        actual.add("3");
        actual.add("4");
        Set<String> existed = new HashSet<>();
        existed.add("1");
        existed.add("3");
        Sets.SetView<String> intersection = Sets.intersection(actual, existed);
        System.out.println("intersection:" + intersection + ". will be do nothing");
        Sets.SetView<String> diffNew = Sets.difference(actual, existed);
        System.out.println("diffNew actual - existed:" + diffNew + ". will be add");
        Sets.SetView<String> diffOld = Sets.difference(existed, actual);
        System.out.println("diff existed - actual:" + diffOld + ". will be remove");
    }
}
