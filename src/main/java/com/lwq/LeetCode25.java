package com.lwq;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class LeetCode25 {

    public static void main(String[] args) {
        //创建链表
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        LeetCode25 leetCode25 = new LeetCode25();
        ListNode temp1 = listNode;
        ListNode temp2 = listNode;
        ListNode listNode1 = leetCode25.reverseKGroup01(temp1, 2);
//        打印listNode1
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }

    /**
     * 链表分区为已翻转部分+待翻转部分+未翻转部分
     * 每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定
     * 需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
     * 初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
     * 经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
     * 翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
     * 特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
     * 时间复杂度为 O(n*K)O(n∗K) 最好的情况为 O(n)O(n) 最差的情况未 O(n^2)O(n^2)
     * 空间复杂度为 O(1)O(1) 除了几个必须的节点指针外，我们并没有占用其他空间
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup01(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseList(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next; //临时节点，暂存当前节点的下一节点，用于后移
            cur.next = pre; //将当前节点指向它前面的节点
            pre = cur; //前指针后移
            cur = nextTemp; //当前指针后移
        }
        return pre;
    }
}
