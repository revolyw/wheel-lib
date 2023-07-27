import java.util.HashMap;
import java.util.Map;

/**
<p>Given an array of integers <code>nums</code>&nbsp;and an integer <code>target</code>, return <em>indices of the two numbers such that they add up to <code>target</code></em>.</p>

<p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not use the <em>same</em> element twice.</p>

<p>You can return the answer in any order.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,11,15], target = 9
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4], target = 6
<strong>Output:</strong> [1,2]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3], target = 6
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li> 
 <li><strong>Only one valid answer exists.</strong></li> 
</ul>

<p>&nbsp;</p> 
<strong>Follow-up:&nbsp;</strong>Can you come up with an algorithm that is less than&nbsp;
<code>O(n<sup>2</sup>)&nbsp;</code>time complexity?

<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 17376</li><li>👎 0</li></div>
*/

public class _1TwoSum {
    public static void main(String[] args) {
         Solution solution = new _1TwoSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 第一题的附加条件要求是时间复杂度 < O(n2)，所以嵌套循环无法满足。
     * 所以我觉得这一题的核心技能点应该是了解空间换时间的思路，并能通过合适的数据结构降低时间复杂度。
     * 这里是用一个哈希表 （HashMap）缓存了中间计算结果，利用一次循环就找到了目标值对。
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * @param nums 输入数组
     * @param target 目标值
     * @return 结果数组。第一个位置是第一个数的下标，第二个位置是第二个数的下标
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            Integer firstIndex = cache.get(result);
            //缓存命中说明之前有一个数和当前数的和等于目标值
            if (null != firstIndex) {
                return new int[]{firstIndex, i};
            }
            cache.put(nums[i], i);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}