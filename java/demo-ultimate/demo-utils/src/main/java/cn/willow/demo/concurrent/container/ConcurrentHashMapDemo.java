package cn.willow.demo.concurrent.container;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author willow
 * @since 2024/12/10
 */
public class ConcurrentHashMapDemo {
    public static final ConcurrentHashMap<String, String> MAP = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        MAP.put("a", "a");
        MAP.get("a");
        MAP.size();
        MAP.isEmpty();
    }
}
