package com.lwq;

import java.util.*;

/**
 *
 */
public class LeetCode141 {

    public static void main(String[] args) {
        //测试用例
        System.out.println(new LeetCode141().hasCycle(null));
        System.out.println(new LeetCode141().hasCycle(new ListNode(1)));
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = listNode;
        System.out.println(new LeetCode141().hasCycle(listNode));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        //如果只有两个节点，并且没有交叉，这里不是用next会报错
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
