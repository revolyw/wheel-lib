/**
 * <p>给定两个大小分别为 <code>m</code> 和 <code>n</code> 的正序（从小到大）数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>。请你找出并返回这两个正序数组的 <strong>中位数</strong> 。</p>
 *
 * <p>算法的时间复杂度应该为 <code>O(log (m+n))</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1,3], nums2 = [2]
 * <strong>输出：</strong>2.00000
 * <strong>解释：</strong>合并数组 = [1,2,3] ，中位数 2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1,2], nums2 = [3,4]
 * <strong>输出：</strong>2.50000
 * <strong>解释：</strong>合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>nums1.length == m</code></li>
 * <li><code>nums2.length == n</code></li>
 * <li><code>0 &lt;= m &lt;= 1000</code></li>
 * <li><code>0 &lt;= n &lt;= 1000</code></li>
 * <li><code>1 &lt;= m + n &lt;= 2000</code></li>
 * <li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>分治</li></div></div><br><div><li>👍 6670</li><li>👎 0</li></div>
 *
 * @author willow
 */

public class _4寻找两个正序数组的中位数 {
    public static void main(String[] args) {
        Solution solution = new _4寻找两个正序数组的中位数().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 本题需要掌握的核心技能是二分查找。需要了解的知识是中位数的定义。
         * 中位数的定义：
         *      当 n 是偶数时，中位数是两个有序数组中的第 n/2 个元素和第 n/2 + 1 个元素的平均值。
         *      当 n 是奇数时，中位数是第 (n+1)/2 个元素的值。
         * 二分查找：每次计算都要将问题域缩小固定倍数，适用于有序集合中查找特定元素。
         *      根据中位数的定义，本题可以转化为，求两个有序数组中的第 k 小的数，其中 k = 两个数组大小之和 / 2。
         *      假设两个数组的长度分别为 m 和 n，要找到第 k 小的元素，我们可以比较第 m 中 k/2 个元素和 n 中第 k/2 个元素，
         *      其中较小元素的前 k/2 个元素不可能是第 k 小的元素，因此可以排除。
         *      然后更新 k 值为排除元素个数后的值，继续重复进行比较直到 k = 1 时，即找到第 k 小的元素。
         *      经过每轮比较都可以排除至多（存在常量种特殊情况） k/2 个元素，即至多 m+n 的一半的元素。所以时间复杂度为 O(log(m+n))
         * 另外可以借由本题强化一下个数和数组下标转换的编码熟练度，做到编码无需思考
         *      例1：计算第 k 个元素的下标 index = k-1
         *      例2：通过先后两个下标 index1、index2 计算偏移量 offset = index2 - index1 + 1
         *      例3：计算下标 index1 根据偏移量 offset 偏移后的下标 index2 = index1 + offset
         * @param nums1 输入数组1
         * @param nums2 输入数组2
         * @return 中位数
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//            return SolutionN(nums1, nums2);
            return SolutionLog(nums1, nums2);
        }

        /**
         * 归并。合并数组然后根据中位数定义返回中位数即可
         * 在本题中，归并思路需要遍历两个数组，所以时间复杂度为 O(m+n)。无法做到题目要求的 O(log(m+n))。
         * 时间复杂度：O(m+n)
         * 空间复杂度：O(m+n)。
         * 其中 m 和 n 分别是数组 num1 和 num2 的长度。
         *
         * @param nums1 输入数组1
         * @param nums2 输入数组2
         * @return 中位数
         */
        private double SolutionN(int[] nums1, int[] nums2) {
            int n1 = nums1.length, n2 = nums2.length;
            int index1 = 0, index2 = 0;
            int n = n1 + n2;
            //空间复杂度：O(n)。 如果不合并数组，可以取消这个数组，直接在合并的过程中计算中位数的位置即可。
            int[] nums = new int[n];
            //合并两个数组。时间复杂度：O(n)
            for (int i = 0; i < n; i++) {
                if (index1 < n1 && index2 < n2) {
                    if (nums1[index1] <= nums2[index2]) {
                        nums[i] = nums1[index1++];
                    } else {
                        nums[i] = nums2[index2++];
                    }
                } else if (index1 >= n1) {
                    nums[i] = nums2[index2++];
                } else {
                    nums[i] = nums1[index1++];
                }
            }
            if (n % 2 == 1) {
                //奇数
                int medianIndex = n / 2;
                return nums[medianIndex];
            } else {
                return (double) (nums[n / 2 - 1] + nums[n / 2]) / 2;
            }
        }

        /**
         * 二分查找
         * 时间复杂度：O(log(m+n)) 其中 m 和 n 分别是数组 num1 和 num2 的长度
         * 空间复杂度：O(1)。
         *
         * @param nums1 输入数组1
         * @param nums2 输入数组2
         * @return 中位数
         */
        private double SolutionLog(int[] nums1, int[] nums2) {
            int n1 = nums1.length, n2 = nums2.length;
            int n = n1 + n2, k = n / 2;
            if (n % 2 == 1) {
                //奇数个元素
                return getKthElement(nums1, nums2, k + 1);
            } else {
                //偶数个元素
                return (getKthElement(nums1, nums2, k) + getKthElement(nums1, nums2, k + 1)) / 2;
            }
        }

        /**
         * 查找两个数组中第 k 小的元素
         * @param nums1 数组1
         * @param nums2 数组2
         * @param k 第 k 小的元素，注意第 k 小的元素的下标对应是 k - 1
         * @return 第 k 小的元素的值
         */
        private double getKthElement(int[] nums1, int[] nums2, int k) {
            int n1 = nums1.length, n2 = nums2.length;
            int index1 = 0, index2 = 0;
            while (true) {
                //特殊情况1：index1 越界
                if (index1 == n1) {
                    return nums2[index2 + k - 1];
                }
                //特殊情况2：index2 越界
                if (index2 == n2) {
                    return nums1[index1 + k - 1];
                }
                //特殊情况3：k=1
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }
                //每次查找排除的元素个数
                int offset;
                //当前比对的下标 = 原下标 + k/2 -1
                int newIndex1 = Math.min(index1 + k/2, n1) - 1;
                int newIndex2 = Math.min(index2 + k/2, n2) - 1;
                if (nums1[newIndex1] > nums2[newIndex2]) {
                    offset = newIndex2 - index2 + 1;
                    index2 = index2 + offset;
                } else {
                    offset = newIndex1 - index1 + 1;
                    index1 = index1 + offset;
                }
                k -= offset;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}