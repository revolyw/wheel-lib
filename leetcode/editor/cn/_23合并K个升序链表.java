import java.util.PriorityQueue;

/**
<p>给你一个链表数组，每个链表都已经按升序排列。</p>

<p>请你将所有链表合并到一个升序链表中，返回合并后的链表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>lists = [[1,4,5],[1,3,4],[2,6]]
<strong>输出：</strong>[1,1,2,3,4,4,5,6]
<strong>解释：</strong>链表数组如下：
[
  1-&gt;4-&gt;5,
  1-&gt;3-&gt;4,
  2-&gt;6
]
将它们合并到一个有序链表中得到。
1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>lists = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>lists = [[]]
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>k == lists.length</code></li> 
 <li><code>0 &lt;= k &lt;= 10^4</code></li> 
 <li><code>0 &lt;= lists[i].length &lt;= 500</code></li> 
 <li><code>-10^4 &lt;= lists[i][j] &lt;= 10^4</code></li> 
 <li><code>lists[i]</code> 按 <strong>升序</strong> 排列</li> 
 <li><code>lists[i].length</code> 的总和不超过 <code>10^4</code></li> 
</ul>

<div><div>Related Topics</div><div><li>链表</li><li>分治</li><li>堆（优先队列）</li><li>归并排序</li></div></div><br><div><li>👍 2918</li><li>👎 0</li></div>
*/

public class _23合并K个升序链表 {
    public static void main(String[] args) {
         Solution solution = new _23合并K个升序链表().new Solution();
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
    public ListNode mergeKLists(ListNode[] lists) {
        //迭代（暴力破解）
//        return solution1(lists);
        //顺序合并
//        return solution2(lists);
        //分治递归合并（借助两两合并）
//        return solution3(lists);
        //使用优先队列（堆）
        return solution4(lists);
    }

    /**
     * 使用优先队列（最小堆）
     * 时间复杂度：考虑优先队列中的元素不超过 k 个，那么插入和删除的时间代价为 O(logk)，
     *  这里最多有 kn 个点，对于每个点都被插入删除各一次，故总的时间代价即渐进时间复杂度为 O(kn×logk)
     * 空间复杂度：这里用了优先队列，优先队列中的元素不超过 k 个，故渐进空间复杂度为 O(k)。
     */
    public ListNode solution4(ListNode[] lists) {
        PriorityQueue<Status> queue = new PriorityQueue<Status>();
        for(ListNode node : lists){
            if(node != null) {
                queue.offer(new Status(node.val, node));
            }
        }

        ListNode head = new ListNode(Integer.MIN_VALUE), tail = head;
        while(!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if(f.ptr.next != null){
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    /**
     * 辅助优先队列按最小堆排序
     */
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr){
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status o){
            return this.val - o.val;
        }
    }

    /**
     * 分治递归合并（借助两两合并）
     * 时间复杂度：渐进时间复杂度为 O(kn×logk)
     * 空间复杂度：递归会使用到 O(logk) 空间代价的栈空间
     */
    public ListNode solution3(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        //分治递归合并（借助两两合并）
        return merge(lists, 0, lists.length-1);
    }

    /**
     * 分治递归合并（借助两两合并）
     * 渐进时间复杂度为 O(kn×logk)。 详见 leetcode
     * 空间复杂度：递归会使用到 O(logk) 空间代价的栈空间。
     */
    public ListNode merge(ListNode[] lists, int l, int r){
        if(l == r) {
            return lists[l];
        }
        if(l > r){
            return null;
        }
        int mid = (l+r)/2;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid+1, r));
    }

    /**
     * 顺序合并（借助两两链表的合并算法)
     * 时间复杂度：故渐进时间复杂度为 O(k^2*n)，详见 leetcode。
     * 空间复杂度：没有用到与 k 和 n 规模相关的辅助空间，故渐进空间复杂度为 O(1)。
     */
    public ListNode solution2(ListNode[] lists) {
        ListNode result = null;
        for(int i=0; i < lists.length; i++){
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    /**
     * 迭代
     * 时间复杂度 O(n*k^2) 其中 n 为数组中链表长度最长的链表的长度，k 为数组的长度。每合并一个元素都要查找一次所有链表头元素的最小值，即遍历 k 次，总共需要合并 k*n 个元素
     * 空间复杂度 O(1)。我们只需要常数的空间存放若干变量。
     */
    public ListNode solution1(ListNode[] lists) {
        //迭代
        //创建新链表用于保存合并结果。创建虚拟节点，用 head 指针指向该节点, 该节点的下一个节点为第一个合并节点
        //用 tail 指针指向最新合并节点的前一个节点，因此初始化时 head = tail;
        //用指针数组 lists[i] 指向数组中每个链表当前第一个未合并的节点
        //循环
        //  用 curMin=null 指针指向当前最小值节点，minIndex 记录最小值节点的下标
        //  遍历 lists[i] 找到最小值节点 curMin 及其下标 minIndex
        //  如果 curMin == null 则 break；
        //  否则 tail.next = curMin; tail = curMin; lists[minIndex]=lists[minIndex].next;
        ListNode head = new ListNode(-Integer.MIN_VALUE, null), tail = head;
        while(true) {
            ListNode curMin = null;
            int minIndex = 0;
            for(int i=0;i<lists.length;i++) {
                if(lists[i] == null){
                    continue;
                }
                if(curMin == null || lists[i].val <= curMin.val){
                    curMin = lists[i];
                    minIndex = i;
                }
            }
            if(curMin == null) {
                break;
            }
            System.out.println(curMin.val);
            tail.next = curMin;
            tail = curMin;
            lists[minIndex] = lists[minIndex].next;
        }
        return head.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
}
//leetcode submit region end(Prohibit modification and deletion)

}