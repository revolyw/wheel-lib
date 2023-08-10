import java.lang.management.ManagementFactory;

/**
 * <p>给你一个字符串 <code>s</code>，找到 <code>s</code> 中最长的回文子串。</p>
 *
 * <p>如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "babad"
 * <strong>输出：</strong>"bab"
 * <strong>解释：</strong>"aba" 同样是符合题意的答案。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "cbbd"
 * <strong>输出：</strong>"bb"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 * <li><code>s</code> 仅由数字和英文字母组成</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 6688</li><li>👎 0</li></div>
 */

public class _5最长回文子串 {
    public static void main(String[] args) {
        Solution solution = new _5最长回文子串().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 最长回文子串解法。本题需要掌握的核心技能是动态规划，动态规划的核心是找到状态转移方程。
         * 另外在实现动态规划时，要注意状态转移的方向，如果是寻找子串，一般是按照子串长度从小到大的顺序来的。
         * <p>
         * 用 dp[i][j] 表示字符串 s 从 i 到 j 的子串是否为回文子串，如果是则为true，否则为false。
         * 那本题的状态转移方程为：dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
         * <p>
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(n^2)
         *
         * @param s 字符串
         * @return 最长回文子串
         */
        public String longestPalindrome(String s) {
            char[] charArray = s.toCharArray();
            // 1 个字符一定是回文子串
            if (charArray.length <= 1) {
                return s;
            }
            // 2 个字符，如果相等，那么原串就是结果，否则第一个字符就是结果
            if (charArray.length == 2) {
                if (charArray[0] == charArray[1]) {
                    return s;
                } else {
                    return s.substring(0, 1);
                }
            }
            // 初始化dp数组。空间复杂度 O(n^2)
            boolean[][] dp = new boolean[charArray.length][charArray.length];
            int maxLength = 0;
            int begin = 0;
            //每种长度的子串都试一下。时间复杂度 O(n^2)，因为循环次数为：n - 1 种长度 * n 种起始下标
            for (int l = 2; l <= charArray.length; l++) {
                for (int i = 0; i < charArray.length; i++) {
                    //子串的结束下标
                    int j = i + l - 1;
                    if (j >= charArray.length) {
                        break;
                    }
                    if (charArray[i] != charArray[j]) {
                        //根据动态规划的状态转移方程，如果首尾字符不相等，那么这个子串一定不是回文子串
                        dp[i][j] = false;
                    } else {
                        if (l <= 3) {
                            //如果子串长度小于等于3，那么只要首尾字符相等，这个子串就是回文子串
                            dp[i][j] = true;
                        } else {
                            /* 如果子串长度大于3，根据动态规划的状态转移方程，要看去掉首尾字符的子串是否为回文子串
                               注意：转移后的结果和外层循环的关系，外层循环是按照子串长度从小到大的顺序来的
                                    所以 dp[i+1][j-1] 一定已经计算过了 */
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    //如果这个子串是回文子串，那么比较后尝试更新最长回文子串的长度和起始下标
                    if (dp[i][j] && l > maxLength) {
                        maxLength = l;
                        begin = i;
                    }
                }
            }
            //如果最长回文子串长度为 0，那么说明原串没有长度大于 1 的回文子串，返回第一个字符
            if (maxLength == 0) {
                return s.substring(0, 1);
            }
            return s.substring(begin, begin + maxLength);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}