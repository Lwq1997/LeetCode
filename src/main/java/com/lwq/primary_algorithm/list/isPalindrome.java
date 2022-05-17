package com.lwq.primary_algorithm.list;


import com.lwq.ListNode;

import java.util.LinkedHashMap;
import java.util.Stack;

/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
 */
public class isPalindrome {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(1);
        a.next = b;
        b.next = c;
        c.next = d;

        System.out.println(isPalindrome(a));
        System.out.println(isPalindrome02(a));
    }

    public static Boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null ){
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    public static Boolean isPalindrome02(ListNode head) {
        if(head == null || head.next == null){
            return Boolean.TRUE;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode pre = null;
        ListNode cur = slow;
        ListNode nxt = slow.next;

        while (nxt != null){
            cur.next = pre;
            pre = cur;
            cur = nxt;
            nxt = cur.next;
        }
        cur.next = pre;

        while (cur.next != null){
            if(cur.val != head.val){
                return false;
            }
            cur = cur.next;
            head = head.next;
        }

        return cur.val == head.val;
    }

}
