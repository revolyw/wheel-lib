package cn.willow.demo.bit;

import java.util.HashMap;

/**
 * @author willow
 * @since 2024/12/12
 */
public class BitDemo {
    public static void main(String[] args) {
//        String obj = "你说什么？";
//        int result = hash(obj);
//        System.out.println("hash code:" + result);
//        System.out.println("hash index:" + obj.hashCode());
        int a = 2;
        System.out.println(1<<10);
    }

    public String printBinary(Object object) {
        return "";
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
