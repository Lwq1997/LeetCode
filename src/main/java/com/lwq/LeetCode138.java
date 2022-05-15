package com.lwq;

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

    class Node {
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
