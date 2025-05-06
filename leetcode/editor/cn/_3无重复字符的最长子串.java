import java.util.HashSet;
import java.util.Set;

/**
 * <p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的&nbsp;<strong>最长子串&nbsp;</strong>的长度。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abcabcbb"
 * <strong>输出: </strong>3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 <span><code>"abc"，所以其</code></span>长度为 3。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "bbbbb"
 * <strong>输出: </strong>1
 * <strong>解释: </strong>因为无重复字符的最长子串是 <span><code>"b"</code></span>，所以其长度为 1。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "pwwkew"
 * <strong>输出: </strong>3
 * <strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<span><code>"wke"</code></span>，所以其长度为 3。
 * &nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<span><code>"pwke"</code></span>&nbsp;是一个<em>子序列，</em>不是子串。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>s</code>&nbsp;由英文字母、数字、符号和空格组成</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 9393</li><li>👎 0</li></div>
 */

public class _3无重复字符的最长子串 {
    public static void main(String[] args) {
        Solution solution = new _3无重复字符的最长子串().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 滑动窗口算法
         * 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次
         * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符）
         * <p>
         * 本题需要掌握的核心技巧就是滑动窗口，尤其是字符串和子串相关的问题中经常会用到这种技巧。滑动窗口一般包含两个指针，一个指向左边界，一个指向右边界。
         * 可以通过移动边界指针来滑动窗口，但在滑动过程中须注意：1.左边界不能超过右边界，2.右边界不能超过输入集合的右边界
         * 每次滑动可以利用窗口内的数据进行一些操作，例如本题的超找重复串。
         * <p>
         * 相关的题目还有：
         * 76.最小覆盖子串
         * 567.字符串的排列
         * 438.找到字符串中所有字母异位词
         * 3.无重复字符的最长子串
         *
         * @param s 输入字符串
         * @return 最长子串长度
         */
        public int lengthOfLongestSubstring(String s) {
            //哈希表记录当前子串字符
            Set<Character> cache = new HashSet<>();
            int maxLength = 0;
            int n = s.length();
            //窗口左边界右移
            for (int left = 0, right = 0; left < n; left++) {
                //窗口右边界右移
                while (right < n && !cache.contains(s.charAt(right))) {
                    cache.add(s.charAt(right));
                    right++;
                }
                //计算最大长度
                maxLength = Math.max(maxLength, right - left);
                //移除最左边的元素
                cache.remove(s.charAt(left));
            }
            return maxLength;
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}