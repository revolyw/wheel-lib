package cn.willow.demo.utils;

/**
 * 位运算工具
 * @author willow
 * @date 2024/7/31
 */
public class BitUtil {
    public static void main(String[] args) {
        int value = 2049;
        int flag = 0x800;
        int result = flag & value;

        System.out.println("flag:" + Integer.toBinaryString(flag));
        System.out.println("value:" + Integer.toBinaryString(value));
        System.out.println("result:" + Integer.toBinaryString(result));
        System.out.println(result > 0);
    }
}
