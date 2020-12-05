//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 5347 👎 0


public class _2两数相加 {
    public static void main(String[] args) {
        Solution solution = new _2两数相加().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * l1.size() = m
         * l2.size() = n
         * 时间复杂度：O(max(m,n))
         * 空间复杂段：O(max(m,n))
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (null == l1) {
                return l2;
            }
            if (null == l2) {
                return l1;
            }
            ListNode head = null;
            ListNode result = null;
            //进位
            boolean hasCarry = false;
            while (null != l1 || null != l2 || hasCarry) {
                if (null == result) {
                    head = result = new ListNode();
                }
                if (null == l1 && null != l2) {
                    int val = l2.val + (hasCarry ? 1 : 0);
                    if (val > 9) {
                        hasCarry = true;
                        val = val - 10;
                    } else {
                        hasCarry = false;
                    }
                    result.next = new ListNode(val);
                    l2 = l2.next;
                    result = result.next;
                } else if (null == l2 && null != l1) {
                    int val = l1.val + (hasCarry ? 1 : 0);
                    if (val > 9) {
                        hasCarry = true;
                        val = val - 10;
                    } else {
                        hasCarry = false;
                    }
                    result.next = new ListNode(val);
                    l1 = l1.next;
                    result = result.next;
                } else if (null != l1 && null != l2) {
                    int val = l1.val + l2.val + (hasCarry ? 1 : 0);
                    if (val > 9) {
                        hasCarry = true;
                        val = val - 10;
                    } else {
                        hasCarry = false;
                    }
                    result.next = new ListNode(val);
                    l1 = l1.next;
                    l2 = l2.next;
                    result = result.next;
                } else {
                    if (hasCarry) {
                        result.next = new ListNode(1);
                        hasCarry = false;
                    }
                }
            }
            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}