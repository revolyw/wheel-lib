package cn.willow.demo.algorithm;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 网易邮箱一面题
 * 7-3 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 括号只考虑 "(" 和 ")" ，有效的括号是指一系列左括号 "(" 和 ")" 组成；但是如果有一些额外的括号，使得括号不能正确配对，就需要删除。
 * 删除规则：从前往后，且尽可能少的删除多余括号。
 * <p>
 * 输入格式:输入一个字符串，字符串的长度小于1000。字符串中只包含字母、 "(" 和 ")"
 * 输出格式: 输出处理后的字符串
 * <p>
 * 输入样例:a)())()
 * 输出样例:a()()
 * <p>
 * 输入:(a)())()
 * 输出：(a)()()
 * 输入：(a(b))
 * 输出：(a(b))
 *
 * @author willow
 * @since 2024/12/11
 */
public class NetEase {
    public static void main(String[] args) {
        {
            String chars = "a)())()";
            String out = clear(chars);
            System.out.println("input:"+chars);
            System.out.println("out:" + out);
        }
        {
            String chars = "(a)())()";
            String out = clear(chars);
            System.out.println("input:"+chars);
            System.out.println("out:" + out);
        }
        {
            String chars = "(a(b))";
            String out = clear(chars);
            System.out.println("input:"+chars);
            System.out.println("out:" + out);
        }
    }

    private static String clear(String chars) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> deque = new LinkedList<>();
        Deque<Character> temp = new LinkedList<>();
        for (char aChar : chars.toCharArray()) {
            if (aChar == ')') {
                boolean valid = false;
                while (!temp.isEmpty()) {
                    if (temp.poll() == '(') {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    deque.push(')');
                }
            } else {
                deque.push(aChar);
                temp.push(aChar);
            }
        }
        if (!temp.isEmpty()) {
            temp.clear();
        }
        while (!deque.isEmpty()) {
            temp.push(deque.poll());
        }
        while (!temp.isEmpty()) {
            sb.append(temp.poll());
        }
        return sb.toString();
    }

}
