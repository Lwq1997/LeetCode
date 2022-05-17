package com.lwq.primary_algorithm.list;


import com.lwq.ListNode;

/*

反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 */
public class reverseList {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;

        ListNode.printself(a);

        System.out.println();

//        ListNode.printself(reverseList(a));

        System.out.println();
        ListNode.printself(reverseList01(a));

    }

    public static ListNode reverseList(ListNode head) {
        if(head ==  null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        pre.next = null;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /*
    我们来看是怎样的一个递归过程：1->2->3->4

        程序到达Node newHead = reverse(head.next);时进入递归
        我们假设此时递归到了3结点，此时head=3结点，temp=3结点.next(实际上是4结点)
        执行Node newHead = reverse(head.next);传入的head.next是4结点，返回的newHead是4结点。
        接下来就是弹栈过程了
        程序继续执行 temp.next = head就相当于4->3
        head.next = null 即把3结点指向4结点的指针断掉。
        返回新链表的头结点newHead

        注意：当retuen后，系统会恢复2结点压栈时的现场，此时的head=2结点；temp=2结点.next(3结点)，再进行上述的操作。最后完成整个链表的翻转。
     */
    public static ListNode reverseList01(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        ListNode newHead = reverseList01(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }
}
