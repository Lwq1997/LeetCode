package com.lwq;

import java.util.List;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->NULL
 * 输出: 3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class LeetCode206 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
//        ListNode relistNode1 = reverseList(listNode1);
        ListNode relistNode2 = reverseList1(listNode1);
        ListNode pre = null;
        while (relistNode2 != null) {
            System.out.println(relistNode2.val);
            pre = relistNode2.next;
            relistNode2 = pre;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        pre.next = null;

        //每一次循环需要反转cur这个节点
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            // 集体向下挪动
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
