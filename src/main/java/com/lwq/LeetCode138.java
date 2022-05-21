package com.lwq;

import java.util.HashMap;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的 深拷贝。 
 * <p>
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 */
public class LeetCode138 {

    public static void main(String[] args) {
        //测试用例
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node1.random = node4;
        node2.random = node8;
        node3.random = node5;
        node4.random = node1;
        node5.random = node3;
        Node node = new LeetCode138().copyRandomList01(node1);
        while (node != null) {
            if (node.random != null) {
                System.out.println("random:" + node.random.val);
            }
            System.out.println("next:" + node.val);
            System.out.println("==========================");
            node = node.next;
        }
    }

    public Node copyRandomList01(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        // 第一步，复制源节点。
        while (p != null) {
            //1-2-3 ==> 1-1-2-2-3-3
            Node p_clone = new Node(p.val);
            Node temp = p.next;
            //把p_clone插入到p和temp中间。
            p.next = p_clone;
            p_clone.next = temp;
            p = temp;
        }
        // 第二步，生成克隆节点的 random 指针。
        p = head;
        while (p != null) {
            if (p.random != null) {
                //random节点之前也被复制了，所以需要 p.random.next
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        // 第三步，拆分
        p = head;
        Node cloneHead = p.next;
        Node cloneP = cloneHead;
        while (cloneP.next != null) {
            //原链表
            p.next = p.next.next;
            p = p.next;
            //克隆链表
            cloneP.next = cloneP.next.next;
            cloneP = cloneP.next;
        }
        //最后不要忘了给原链表收尾
        p.next = null;
        return cloneHead;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
