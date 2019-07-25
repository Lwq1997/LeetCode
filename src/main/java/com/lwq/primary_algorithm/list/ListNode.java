package com.lwq.primary_algorithm.list;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void printself(ListNode node){
        while (node != null){
            System.out.print(node.val+ " ");
            node = node.next;
        }
    }
}