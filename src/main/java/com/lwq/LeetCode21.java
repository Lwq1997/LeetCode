package com.lwq;


/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class LeetCode21 {
    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);

        p1.next = p3;
        p2.next = p4;
        p3.next = p5;
        p4.next = p6;
        ListNode head = mergeTwoLists01(p1, p2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }

    /**
     * 归并
     *
     * @param p1
     * @param p2
     * @return
     */
    public static ListNode mergeTwoLists01(ListNode p1, ListNode p2) {
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                head.next = p2;
                p2 = p2.next;
                head = head.next;
            } else {
                head.next = p1;
                p1 = p1.next;
                head = head.next;
            }
        }
        if (p1 == null) {
            head.next = p2;
        } else {
            head.next = p1;
        }
        return pre.next;
    }

    /**
     * 递归
     *
     * @param p1
     * @param p2
     * @return
     */
    public ListNode mergeTwoLists02(ListNode p1, ListNode p2) {
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
            head.next = mergeTwoLists02(p1.next, p2);
        }
        if (p1.val > p2.val) {
            head = p2;
            head.next = mergeTwoLists02(p1, p2.next);
        }
        return head;
    }
}
