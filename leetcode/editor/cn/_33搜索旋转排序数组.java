import jdk.nashorn.internal.objects.annotations.Where;

/**
<p>整数数组 <code>nums</code> 按升序排列，数组中的值 <strong>互不相同</strong> 。</p>

<p>在传递给函数之前，<code>nums</code> 在预先未知的某个下标 <code>k</code>（<code>0 &lt;= k &lt; nums.length</code>）上进行了 <strong>旋转</strong>，使数组变为 <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code>（下标 <strong>从 0 开始</strong> 计数）。例如， <code>[0,1,2,4,5,6,7]</code> 在下标 <code>3</code> 处经旋转后可能变为&nbsp;<code>[4,5,6,7,0,1,2]</code> 。</p>

<p>给你 <strong>旋转后</strong> 的数组 <code>nums</code> 和一个整数 <code>target</code> ，如果 <code>nums</code> 中存在这个目标值 <code>target</code> ，则返回它的下标，否则返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>你必须设计一个时间复杂度为 <code>O(log n)</code> 的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [<span><code>4,5,6,7,0,1,2]</code></span>, target = 0
<strong>输出：</strong>4
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [<span><code>4,5,6,7,0,1,2]</code></span>, target = 3
<strong>输出：</strong>-1</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], target = 0
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 5000</code></li> 
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
 <li><code>nums</code> 中的每个值都 <strong>独一无二</strong></li> 
 <li>题目数据保证 <code>nums</code> 在预先未知的某个下标上进行了旋转</li> 
 <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 3066</li><li>👎 0</li></div>
*/

public class _33搜索旋转排序数组 {
    public static void main(String[] args) {
         Solution solution = new _33搜索旋转排序数组().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1 && nums[0] == target) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if(target == nums[left]){
                return left;
            }
            if(target == nums[right]){
                return right;
            }
            int mid = left + (right - left) / 2;
            boolean inLeft = false;
            if(nums[left] <= nums[mid]) {
                //左递增
                if(target > nums[left] && target <= nums[mid]){
                    //在左区间
                    inLeft = true;
                } else {
                    //在右区间
                    inLeft = false;
                }
            } else {
                //右递增
                if(target >= nums[mid+1] && target < nums[right]){
                    //在右区间
                    inLeft = false;
                } else {
                    //在左区间
                    inLeft = true;
                }
            }
            if(inLeft) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}