/**
<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>

<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>

<p>你必须设计并实现时间复杂度为 <code>O(n)</code> 的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <span><code>[3,2,1,5,6,4],</code></span> k = 2
<strong>输出:</strong> 5
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> <span><code>[3,2,3,1,2,4,5,5,6], </code></span>k = 4
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>提示： </strong></p>

<ul> 
 <li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 2604</li><li>👎 0</li></div>
*/

public class _215数组中的第K个最大元素 {
    public static void main(String[] args) {
         Solution solution = new _215数组中的第K个最大元素().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int findKthLargest(int[] nums, int k) {
            //快选
//            return solution1(nums, k);
            //最小堆
            return solution2(nums, k);
        }

        /**
         * 快选
         */
        public int solution1(int[] nums, int k) {
            int n = nums.length;
            int result = quickSelect(nums, 0, n - 1, n - k);
            return result;
        }

        public int quickSelect(int[] nums, int l, int r, int k) {
            int pivot = nums[l], i=l, j=r;
            if(l == r) {
                return nums[k];
            }
            while(i<j){
                do i++; while(nums[i] < pivot);
                do j--; while(nums[j] > pivot);
                if(i<j){
                    swap(nums, i, j);
                }
            }
            if(k <= j){
                return quickSelect(nums, l, j, k);
            } else {
                return quickSelect(nums, j+1, r, k);
            }
        }

//        public int quickSelect(int[] nums, int l, int r, int k) {
//            if(l == r) {
//                return nums[k];
//            }
//            int pivot = nums[l], i=l-1, j=r+1;
//            while(i<j){
//                do i++; while(nums[i] < pivot);
//                do j--; while(nums[j] > pivot);
//                if(i<j){
//                    swap(nums, i, j);
//                }
//            }
//            if(k <= j){
//                return quickSelect(nums, l, j, k);
//            } else {
//                return quickSelect(nums, j+1, r, k);
//            }
//        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        /**
         * 堆
         */
        public int solution2(int[] nums, int k) {
            return 0;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}