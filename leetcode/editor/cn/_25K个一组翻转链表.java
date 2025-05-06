/**
<p>给你链表的头节点 <code>head</code> ，每&nbsp;<code>k</code><em>&nbsp;</em>个节点一组进行翻转，请你返回修改后的链表。</p>

<p><code>k</code> 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是&nbsp;<code>k</code><em>&nbsp;</em>的整数倍，那么请将最后剩余的节点保持原有顺序。</p>

<p>你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg" style="width: 542px; height: 222px;" /> 
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[2,1,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg" style="width: 542px; height: 222px;" /></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 3
<strong>输出：</strong>[3,2,1,4,5]
</pre>

<p>&nbsp;</p> 
<strong>提示：</strong>

<ul> 
 <li>链表中的节点数目为 <code>n</code></li> 
 <li><code>1 &lt;= k &lt;= n &lt;= 5000</code></li> 
 <li><code>0 &lt;= Node.val &lt;= 1000</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个只用 <code>O(1)</code> 额外内存空间的算法解决此问题吗？</p>

<ul> 
</ul>

<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2440</li><li>👎 0</li></div>
*/

public class _25K个一组翻转链表 {
    public static void main(String[] args) {
         Solution solution = new _25K个一组翻转链表().new Solution();
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k < 1){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(head != null) {
            ListNode tail = pre;
            //计算当前组的节点数，如果小于 k 则直接返回
            for(int i=0; i< k; i++){
                tail = tail.next;
                if(null == tail){
                    return dummy.next;
                }
            }
            //按组反转
            ListNode nex = tail.next;
            ListNode[] result = reverseSubGroup(head);
            head = result[0];
            tail = result[1];
            //接入到上一个链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return dummy.next;
    }

    public ListNode[] reverseSubGroup(ListNode head) {
        if(null == head || null == head.next){
            return new ListNode[]{head, head};
        }
        ListNode cur = head, before = null, after = head.next;
        while(null != after.next) {
            cur.next = before;
            before = cur;
            cur = after;
            after = after.next;
        }
        cur.next = before;
        return new ListNode[]{cur, head};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}