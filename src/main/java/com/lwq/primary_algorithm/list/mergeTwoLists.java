package com.lwq.primary_algorithm.list;


/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
 */
public class mergeTwoLists {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;

        ListNode a1 = new ListNode(11);
        ListNode b1 = new ListNode(21);
        ListNode c1 = new ListNode(31);
        ListNode d1 = new ListNode(41);
        a1.next = b1;
        b1.next = c1;
        c1.next = d1;

        ListNode.printself(a);

        System.out.println();

        ListNode.printself(a1);

        System.out.println();


        System.out.println();
        ListNode.printself(mergeTwoLists(a, a1));

    }

    public static ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        if (p1 == null && p2 == null) {
            return null;
        }
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }

        ListNode head = null;
        if (p1.val <= p2.val) {
            head = p1;
            head.next = mergeTwoLists(p1.next, p2);
        }
        if (p1.val > p2.val) {
            head = p2;
            head.next = mergeTwoLists(p1, p2.next);
        }
        return head;
    }
}
