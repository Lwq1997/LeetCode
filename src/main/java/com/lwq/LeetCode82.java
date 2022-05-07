package com.lwq;

import java.util.List;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class LeetCode82 {
    public static void main(String[] args) {
        LeetCode82 leetCode82 = new LeetCode82();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(1);
        listNode.next.next.next = new ListNode(2);
        listNode.next.next.next.next = new ListNode(3);
        ListNode listNode1 = leetCode82.deleteDuplicates(listNode);
        while (listNode1 != null){
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode shaobing = new ListNode(-1);
        shaobing.next = head;
        ListNode curr = shaobing;
        while (curr.next != null && curr.next.next != null){
            if(curr.next.val == curr.next.next.val){
                ListNode temp = curr.next;
                while (temp != null && temp.next != null && temp.val == temp.next.val ) {
                    temp = temp.next;
                }
                curr.next = temp.next;
            }else {
                // 如果下面两个节点的值不是重复的值，curr就向下跳
                curr = curr.next;
            }
        }
        return shaobing.next;
    }
}
