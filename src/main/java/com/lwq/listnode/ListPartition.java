package com.lwq.listnode;

import com.lwq.DoubleNode;
import com.lwq.ListNode;

//给定一个链表和一个节点，小于节点的放左边，等于的放中间，大于的放右边
public class ListPartition {
    public static void main(String[] args) {
        //1==4==3==2==5==2
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(2);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(2);
        ListNode partition = partition(listNode, 3);
        while (partition != null) {
            System.out.println(partition.val);
            partition = partition.next;
        }
    }

    private static ListNode partition(ListNode head, int i) {

        ListNode sl = null;//小于i的链表头
        ListNode sr = null;//小于i的链表尾
        ListNode el = null;//等于i的链表头
        ListNode er = null;//等于i的链表尾
        ListNode ml = null;//大于i的链表头
        ListNode mr = null;//大于i的链表尾
        while (head != null) {
            ListNode next = head.next;
            head.next = null;//这里要切断原链表的顺序，不然会出现循环
            if (head.val < i) {
                if (sl == null) {
                    //如果一开始没有小于i的节点，那么把当前节点作为小于i的链表头和尾
                    sl = head;
                    sr = head;
                } else {
                    //小于i的链表尾指向当前节点
                    sr.next = head;
                    sr = head;
                }
            } else if (head.val == i) {
                if (el == null) {
                    //如果一开始没有等于i的节点，那么把当前节点作为小于i的链表头和尾
                    el = head;
                    er = head;
                } else {
                    //等于i的链表尾指向当前节点
                    er.next = head;
                    er = head;
                }
            } else {
                if (ml == null) {
                    //如果一开始没有等于i的节点，那么把当前节点作为小于i的链表头和尾
                    ml = head;
                    mr = head;
                } else {
                    //等于i的链表尾指向当前节点
                    mr.next = head;
                    mr = head;
                }
            }
            head = next;
        }
        //1==4==3==2==5==2
        //小于：1==2==2
        //等于：3
        //大于：4==5
        if(sr != null){//如果有小于区域
            sr.next = el;
            er = er == null ? sr : er; //谁去链接大于区域的头，谁就变成er
        }
        if(er != null){//如果有等于区域
            er.next = ml;
        }
        return sl != null ? sl : (el != null ? el : ml);
    }


}
