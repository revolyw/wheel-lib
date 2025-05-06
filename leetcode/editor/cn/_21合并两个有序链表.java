/**
<p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" /> 
<pre>
<strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
<strong>输出：</strong>[1,1,2,3,4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>两个链表的节点数目范围是 <code>[0, 50]</code></li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 <li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li> 
</ul>

<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 3646</li><li>👎 0</li></div>
*/

public class _21合并两个有序链表 {
    public static void main(String[] args) {
         Solution solution = new _21合并两个有序链表().new Solution();
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 迭代
//        return solution1(list1, list2);
        // 递归
        return solution2(list1, list2);
    }

    /**
     * 迭代
     * 时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)。
     * 空间复杂度：O(1)。我们只需要常数的空间存放若干变量。
     */
    public ListNode solution1(ListNode list1, ListNode list2) {
        //构建新链表。创建一个虚拟节点，并且用 head 指针指向它，tail 指针指向新链表最后一个非虚拟节点的前一个节点
        //用 aCur 和 bCur 两个指针指向，两个有序链表当前未参与合并的第一个节点
        //当 aCur 和 bCur 均不为空时进入循环。
        //如果 aCur.val <= bCur.val 则 tail.next=aCur, tail = aCur, aCur=aCur.next; continue;
        //否则 tail.next=bCur, tail = bCur, bCur=bCur.next; continue;
        //如果 aCur 为空 && bCur 不为空，则 tail.next = bCur;
        //如果 bCur 为空 && aCur 不为空，则 tail.next = aCur;
        //返回 head.next

        ListNode head = new ListNode(-200, null), tail = head, aCur = list1, bCur = list2;
        while(aCur != null && bCur != null){
            if(aCur.val <= bCur.val){
                tail.next = aCur;
                tail = aCur;
                aCur = aCur.next;
            } else {
                tail.next = bCur;
                tail = bCur;
                bCur = bCur.next;
            }
        }
        if(aCur == null && bCur != null){
            tail.next = bCur;
        }
        if(bCur == null && aCur != null){
            tail.next = aCur;
        }
        return head.next;
    }

    /**
     * 递归
     * 时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
     * 空间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。
     */
    public ListNode solution2(ListNode list1, ListNode list2) {
        //构建新链表。创建一个虚拟节点，并且用 head 指针指向它
        // list1 和 list2 作为指针，分别指向两个链表当前未合并的第一个元素
        // 边界情况
        //如果 list1 为空，return list2;
        //如果 list2 为空，return list1;
        // 递归
        //如果 list1.val <= list2.val 则 head->next = list1, list1.next = mergeTwoLists(list1.next, list2);
        //否则 head->next = list2, list2.next = mergeTwoLists(list1, list2.next);
        // return head.next
        ListNode head = new ListNode(-200, null);
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        if(list1.val <= list2.val) {
            head.next = list1;
            list1.next = mergeTwoLists(list1.next, list2);
        } else {
            head.next = list2;
            list2.next = mergeTwoLists(list1, list2.next);
        }

        return head.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}