package cn.willow.demo.base.list;

/**
 * 带头指针的单链表原地逆序(逆序函数为reverse)
 * @author willow
 * @since 2024/9/3
 */
public class LinkedListDemo {
    //测试入口
    public static void main(String[] args) {
        {
            List.Node n7 = new List.Node(7, null);
            List.Node n6 = new List.Node(6, n7);
            List.Node n5 = new List.Node(5, n6);
            List.Node n4 = new List.Node(4, n5);
            List.Node n3 = new List.Node(3, n4);
            List.Node n2 = new List.Node(2, n3);
            List.Node n1 = new List.Node(1, n2);
            List.Node head = new List.Node(0, n1);
            List list = new List(head);
            list.traverse();
            list.reverse();
            list.traverse();
        }

        {
            List.Node n2 = new List.Node(2, null);
            List.Node n1 = new List.Node(1, n2);
            List.Node head = new List.Node(0, n1);
            List list = new List(head);
            list.traverse();
            list.reverse();
            list.traverse();
        }
    }

    static class List {
        Node head;
        public List(Node head) {
            this.head = head;
        }
        //节点
        static class Node {
            int data;
            Node next;
            public Node() {}
            public Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
        }
        //逆序
        public void reverse() {
            if (null == head || null == head.next || null == head.next.next) {
                //头节点为空/除头节点外，无数据节点/只有一个数据节点
                return;
            }
            Node pre = null, cur = this.head.next, nex = cur.next;
            while (null != nex) {
                //1. 逆置当前节点指向
                cur.next = pre;
                //2. 前置、当前、后置节点的三个指针向前移动
                pre = cur;
                cur = nex;
                nex = nex.next;
            }
            cur.next = pre;//3. 逆置最后一个节点的指向
            this.head.next = cur;//4. 头指针指向最后一个节点
        }
        //遍历
        public void traverse() {
            if (null == head.next)
                return;
            Node cur = head.next;
            while (null != cur) {
                System.out.println(cur.data);
                cur = cur.next;
            }
            System.out.println("traverse end \n");
        }
    }
}
