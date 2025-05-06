import java.math.BigDecimal;
import java.math.RoundingMode;

/**
<p>给你一个由 <code>n</code> 个元素组成的整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>请你找出平均数最大且 <strong>长度为 <code>k</code></strong> 的连续子数组，并输出该最大平均数。</p>

<p>任何误差小于 <code>10<sup>-5</sup></code> 的答案都将被视为正确答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,12,-5,-6,50,3], k = 4
<strong>输出：</strong>12.75
<strong>解释：</strong>最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5], k = 1
<strong>输出：</strong>5.00000
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>n == nums.length</code></li> 
 <li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>滑动窗口</li></div></div><br><div><li>👍 363</li><li>👎 0</li></div>
*/

public class _643子数组最大平均数I {
    public static void main(String[] args) {
         Solution solution = new _643子数组最大平均数I().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        if(n == 1 && k == 1) {
            return nums[0];
        }
        //计算 k 大小的子数组和
        int sum = 0;
        for(int i=0; i<k; i++){
            sum += nums[i] ;
        }
        int maxSum = sum;
        for(int i=k; i<n; i++){
            sum = sum + nums[i] - nums[i-k];
            maxSum = Math.max(sum, maxSum);
        }
        return 1.0 * maxSum/k;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}