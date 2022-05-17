package com.lwq;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static void printself(ListNode node){
        while (node != null){
            System.out.print(node.val+ " ");
            node = node.next;
        }
        System.out.println();
    }
}
