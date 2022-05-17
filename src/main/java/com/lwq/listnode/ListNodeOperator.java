package com.lwq.listnode;

import com.lwq.DoubleNode;
import com.lwq.ListNode;

public class ListNodeOperator {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        ListNode.printself(listNode);
        ListNode listNode1 = reverseList(listNode);
        ListNode.printself(listNode1);

        DoubleNode doubleNode01 = new DoubleNode(1);
        DoubleNode doubleNode02 = new DoubleNode(2);
        DoubleNode doubleNode03 = new DoubleNode(3);
        doubleNode01.next = doubleNode02;
        doubleNode02.next = doubleNode03;
        doubleNode03.last = doubleNode02;
        doubleNode02.last = doubleNode01;
        DoubleNode.printascself(doubleNode01);
        DoubleNode.printdescself(doubleNode03);
        DoubleNode doubleNode001 = reverseDoubleList(doubleNode01);
        DoubleNode.printascself(doubleNode001);
        DoubleNode.printdescself(doubleNode001.next.next);
    }

    private static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null,next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode pre = null,next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
