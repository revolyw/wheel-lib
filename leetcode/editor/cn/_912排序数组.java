import java.util.Stack;

/**
 * <p>给你一个整数数组&nbsp;<code>nums</code>，请你将该数组升序排列。</p>
 *
 * <p>你必须在 <strong>不使用任何内置函数</strong> 的情况下解决问题，时间复杂度为 <code>O(nlog(n))</code>，并且空间复杂度尽可能小。</p>
 *
 * <p>&nbsp;</p>
 *
 * <ol>
 * </ol>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [5,2,3,1]
 * <strong>输出：</strong>[1,2,3,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [5,1,1,2,0,0]
 * <strong>输出：</strong>[0,0,1,1,2,5]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>-5 * 10<sup>4</sup> &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>桶排序</li><li>计数排序</li><li>基数排序</li><li>排序</li><li>堆（优先队列）</li><li>归并排序</li></div></div><br><div><li>👍 1059</li><li>👎 0</li></div>
 */

public class _912排序数组 {
    public static void main(String[] args) {
        Solution solution = new _912排序数组().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSort(int[] input, int low, int high) {
            Stack<Integer[]> stack = new Stack<>();
            stack.push(new Integer[]{low, high});
            while (!stack.isEmpty()) {
                Integer[] top = stack.pop();
                low = top[0];
                high = top[1];
                if (low > high) {
                    continue;
                }
                int pivotIndex = partition(input, low, high);
                stack.push(new Integer[]{pivotIndex + 1, high});
                stack.push(new Integer[]{low, pivotIndex - 1});
            }

        }

        private int partition(int[] input, int low, int high) {
            int pivotIndex = chosePivotIndex(low, high);
            final int pivot = input[pivotIndex];
            swap(input, pivotIndex, high);
            pivotIndex = high;
            int left = low, right = high;
            while (left < right) {
                while (left < right && input[left] <= pivot) {
                    left++;
                }
                while (left < right && input[right] >= pivot) {
                    right--;
                }
                swap(input, left, right);
            }
            swap(input, left, pivotIndex);
            return left;
        }

        private int chosePivotIndex(int low, int high) {
            return low + (int) (Math.random() * (high - low + 1));
        }

        private void swap(int[] input, int left, int right) {
            if (input[left] == input[right]) {
                return;
            }
            input[left] = input[left] ^ input[right];
            input[right] = input[left] ^ input[right];
            input[left] = input[left] ^ input[right];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}