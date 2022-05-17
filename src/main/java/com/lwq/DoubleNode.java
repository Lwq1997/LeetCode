package com.lwq;

public class DoubleNode {
    public int val;
    public DoubleNode last;
    public DoubleNode next;

    public DoubleNode(int x) {
        val = x;
    }

    public static void printascself(DoubleNode node){
        while (node != null){
            System.out.print(node.val+ " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void printdescself(DoubleNode node){
        while (node != null){
            System.out.print(node.val+ " ");
            node = node.last;
        }
        System.out.println();
    }
}