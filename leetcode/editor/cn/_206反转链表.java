/**
给你单链表的头节点 <code>head</code> ，请你反转链表，并返回反转后的链表。

<div class="original__bRMd"> 
 <div> 
  <p>&nbsp;</p> 
 </div>
</div>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg" style="width: 542px; height: 222px;" /> 
<pre>
<strong>输入：</strong>head = [1,2,3,4,5]
<strong>输出：</strong>[5,4,3,2,1]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg" style="width: 182px; height: 222px;" /> 
<pre>
<strong>输入：</strong>head = [1,2]
<strong>输出：</strong>[2,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点的数目范围是 <code>[0, 5000]</code></li> 
 <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？</p>

<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 3732</li><li>👎 0</li></div>
*/

public class _206反转链表 {
    public static void main(String[] args) {
         Solution solution = new _206反转链表().new Solution();
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
    public ListNode reverseList(ListNode head) {
        if(null == head || null == head.next){
            return head;
        }
        //迭代
        ListNode cur = head, before = null, after = head.next;
        while(after != null) {
            cur.next = before;
            before = cur;
            cur = after;
            after = after.next;
        }
        cur.next = before;
        return cur;
        //递归
//        ListNode newHead = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}