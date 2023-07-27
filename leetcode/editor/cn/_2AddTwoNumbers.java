/**
<p>You are given two <strong>non-empty</strong> linked lists representing two non-negative integers. The digits are stored in <strong>reverse order</strong>, and each of their nodes contains a single digit. Add the two numbers and return the sum&nbsp;as a linked list.</p>

<p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg" style="width: 483px; height: 342px;" /> 
<pre>
<strong>Input:</strong> l1 = [2,4,3], l2 = [5,6,4]
<strong>Output:</strong> [7,0,8]
<strong>Explanation:</strong> 342 + 465 = 807.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> l1 = [0], l2 = [0]
<strong>Output:</strong> [0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
<strong>Output:</strong> [8,9,9,9,0,0,0,1]
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li>The number of nodes in each linked list is in the range <code>[1, 100]</code>.</li> 
 <li><code>0 &lt;= Node.val &lt;= 9</code></li> 
 <li>It is guaranteed that the list represents a number that does not have leading zeros.</li> 
</ul>

<div><div>Related Topics</div><div><li>递归</li><li>链表</li><li>数学</li></div></div><br><div><li>👍 9821</li><li>👎 0</li></div>
*/

public class _2AddTwoNumbers {
    public static void main(String[] args) {
         Solution solution = new _2AddTwoNumbers().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        return enumerationCase(l1, l2);
        return linkList(l1, l2);
    }

    /**
     * 两数相加的核心是处理好进位
     * 而我认为这一题的核心技能点是链表操作：对于链表问题，返回结果为头结点时，通常需要先初始化一个前置指针 pre，该指针的下一个节点是真正的头结点。
     * 使用前置指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
     * 时间复杂度：O(max(m,n))
     * 空间复杂段：O(max(m,n))
     * @param l1 链表1
     * @param l2 链表2
     * @return 结果链表
     */
    private ListNode linkList(ListNode l1, ListNode l2) {
        //前置指针
        ListNode pre = new ListNode(0);
        //头节点指针
        ListNode cur = pre;
        //进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry == 1) {
            //最后一位进位
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 枚举情况
     *
     * l1.size() = m
     * l2.size() = n
     * 时间复杂度：O(max(m,n))
     * 空间复杂段：O(max(m,n))
     * @param l1
     * @param l2
     * @return
     */
    private ListNode enumerationCase(ListNode l1, ListNode l2) {
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
            if (null != l1 && null != l2) {
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
            } else if (null == l1 && null != l2) {
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