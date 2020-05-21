package com.lwq;

/**
 *在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class LeetCode148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        System.out.println(sortList(head));
    }

    public  static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 使用快慢指针分割连表
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        // 在这里切断链表
        slow.next = null;

        // 分别递归排序左，右两部分
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode shaobing = new ListNode(-1);
        ListNode res = shaobing;

        // 合并两个有序的链表，left开头的有序连表和right开头的有序链表,这两个链表的长度最多差1个
        shaobing.next = mergeTwoLists(left,right);
        return res.next;
    }

    public static ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        if(p1 == null && p2== null){
            return null;
        }
        if(p1== null){
            return p2;
        }
        if(p2 == null){
            return p1;
        }
        ListNode head = null;
        if(p1.val<p2.val){
            head = p1;
            head.next = mergeTwoLists(p1.next,p2);
        }else{
            head = p2;
            head.next = mergeTwoLists(p1,p2.next);
        }
        return head;
    }
}
