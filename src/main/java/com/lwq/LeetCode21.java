package com.lwq;


/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */
public class LeetCode21 {
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        if(p1 == null && p2 == null){
            return null;
        }
        if(p1 == null){
            return p2;
        }
        if(p2 == null){
            return p1;
        }

        ListNode head = null;
        if (p1.val <= p2.val){
            head = p1;
            head.next = mergeTwoLists(p1.next,p2);
        }
        if (p1.val > p2.val){
            head = p2;
            head.next = mergeTwoLists(p1,p2.next);
        }
        return head;
    }
}
