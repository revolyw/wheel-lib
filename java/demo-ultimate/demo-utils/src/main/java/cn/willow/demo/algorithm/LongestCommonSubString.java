package cn.willow.demo.algorithm;

import org.springframework.util.Assert;

/**
 * 字节一面算法真题
 * 求两个字符串的最长公共子串
 * @author willow
 * @since 2025/3/19
 */
public class LongestCommonSubString {
    public static void main(String[] args) {
        {
            String a = "12ddd", b = "12hhh";
            String c = solution(a, b);
            System.out.println(c);
            Assert.isTrue("12".equals(c), "wrong");
        }
        {
            String a = "12ddd", b = "12dddhhh";
            String c = solution(a, b);
            System.out.println(c);
            assert "12ddd".equals(c);
        }
        {
            String a = "abcdef", b = "abcdef";
            String c = solution(a, b);
            System.out.println(c);
            assert "abcdef".equals(c);
        }
        {
            String a = "abcdef", b = "ghijkl";
            String c = solution(a, b);
            System.out.println(c);
            assert "".equals(c);
        }
        {
            String a = "abcdef", b = "zabcf";
            String c = solution(a, b);
            System.out.println(c);
            assert "abc".equals(c);
        }
        {
            String a = "", b = "abcdef";
            String c = solution(a, b);
            System.out.println(c);
            assert "".equals(c);
        }
        {
            String a = "ababab", b = "bababa";
            String c = solution(a, b);
            System.out.println(c);
            assert "ababa".equals(c);
        }
    }

    public static String solution(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        String l,s;
        if (n1 >= n2) {
            l = a;
            s = b;
        } else {
            l = b;
            s = a;
        }
        String ans = "";
        int ansLength = 0;
        char[] chars = l.toCharArray();
        int left = 0, right = 1;
        while (left <= right && right <= chars.length) {
            String substring = l.substring(left, right);
            if (s.contains(substring)) {
                if (substring.length() > ansLength) {
                    ansLength = substring.length();
                    ans = substring;
                }
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }
}
