package com.lwq;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class LeetCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if(left == right){
            return lists[left];
        }
        int mid = (left+right)/2;
        ListNode l1 = merge(lists,left,mid);
        ListNode l2 = merge(lists,mid+1,right);
        return mergeTwoLists(l1,l2);
    }
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
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

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for (ListNode list : lists) {
            queue.add(list);
        }
        ListNode p = new ListNode(-1);
        ListNode res = p;
        while (!queue.isEmpty()){
            // 弹出的一定是最小的一个
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) {
                // 把弹出的那个链表的下一个节点加入
                queue.add(p.next);
            }
        }
        return res.next;
    }
}
