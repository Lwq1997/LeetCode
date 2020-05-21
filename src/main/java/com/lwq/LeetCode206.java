package com.lwq;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class LeetCode206 {
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        pre.next = null;

        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            // 集体向下挪动
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
