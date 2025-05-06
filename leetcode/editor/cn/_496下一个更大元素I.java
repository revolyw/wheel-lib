/**
<p><code>nums1</code>&nbsp;中数字&nbsp;<code>x</code>&nbsp;的 <strong>下一个更大元素</strong> 是指&nbsp;<code>x</code>&nbsp;在&nbsp;<code>nums2</code> 中对应位置 <strong>右侧</strong> 的 <strong>第一个</strong> 比&nbsp;<code>x</code><strong>&nbsp;</strong>大的元素。</p>

<p>给你两个<strong> 没有重复元素</strong> 的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code> ，下标从 <strong>0</strong> 开始计数，其中<code>nums1</code>&nbsp;是&nbsp;<code>nums2</code>&nbsp;的子集。</p>

<p>对于每个 <code>0 &lt;= i &lt; nums1.length</code> ，找出满足 <code>nums1[i] == nums2[j]</code> 的下标 <code>j</code> ，并且在 <code>nums2</code> 确定 <code>nums2[j]</code> 的 <strong>下一个更大元素</strong> 。如果不存在下一个更大元素，那么本次查询的答案是 <code>-1</code> 。</p>

<p>返回一个长度为&nbsp;<code>nums1.length</code> 的数组<em> </em><code>ans</code><em> </em>作为答案，满足<em> </em><code>ans[i]</code><em> </em>是如上所述的 <strong>下一个更大元素</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,1,2], nums2 = [1,3,4,2].
<strong>输出：</strong>[-1,3,-1]
<strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
- 4 ，用加粗斜体标识，nums2 = [1,3,<strong>4</strong>,2]。不存在下一个更大元素，所以答案是 -1 。
- 1 ，用加粗斜体标识，nums2 = [<em><strong>1</strong></em>,3,4,2]。下一个更大元素是 3 。
- 2 ，用加粗斜体标识，nums2 = [1,3,4,<em><strong>2</strong></em>]。不存在下一个更大元素，所以答案是 -1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,4], nums2 = [1,2,3,4].
<strong>输出：</strong>[3,-1]
<strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
- 2 ，用加粗斜体标识，nums2 = [1,<em><strong>2</strong></em>,3,4]。下一个更大元素是 3 。
- 4 ，用加粗斜体标识，nums2 = [1,2,3,<em><strong>4</strong></em>]。不存在下一个更大元素，所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li> 
 <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li> 
 <li><code>nums1</code>和<code>nums2</code>中所有整数 <strong>互不相同</strong></li> 
 <li><code>nums1</code> 中的所有整数同样出现在 <code>nums2</code> 中</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(nums1.length + nums2.length)</code> 的解决方案吗？</p>

<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>哈希表</li><li>单调栈</li></div></div><br><div><li>👍 1223</li><li>👎 0</li></div>
*/

public class _496下一个更大元素I {
    public static void main(String[] args) {
         Solution solution = new _496下一个更大元素I().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
//        return solution1(nums1, nums2);
        return solution2(nums1, nums2);
    }

        /**
         * 单调栈
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] solution2(int[] nums1, int[] nums2) {
            int n1 = nums1.length;
            int n2 = nums2.length;
            //根据 nums2 构建单调栈，元素为值+下标
            //遍历 nums1 对每个元素判断与单调栈栈顶元素是否相等，相等则将栈顶元素出栈，直到栈为空或者栈顶元素大于当前元素，此时栈顶元素就是当前元素的下一个更大元素
            int[] ans = new int[n1];
            return ans;
        }

    /**
     * 暴力破解
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] solution1(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] ans = new int[n1];
        for(int i=0; i<n1; i++) {
            ans[i] = -1;
            int org = nums1[i];
            boolean located = false;
            boolean find = false;
            for(int j=0; j<n2; j++) {
                if(nums2[j] == org) {
                    located = true;
                }
                if(located){
                    if(nums2[j] > org) {
                        ans[i] = nums2[j];
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}