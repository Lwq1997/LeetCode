package com.lwq;

import java.util.List;

/**
 * 对链表进行插入排序。
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *  
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre;
        dummy.next = head;

        while (head != null && head.next != null){
            // head表示已经排好序的节点，如果当前节点小于等于下一个节点，则不用排序
            if(head.val <= head.next.val){
                head = head.next;
                continue;
            }
            //这时候说明head.next比较小，就需要往前插入
            pre = dummy;
            while (pre.next.val < head.next.val) {
                // pre找到head.next应该插入的位置
                pre = pre.next;
            }
            // 找到位置后把head.next插入pre的next位置
            ListNode curr = head.next;
            // head需要继续下移
            head.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
        }
        // 返回哨兵节点的下一个节点
        return dummy.next;
    }

    /**
     * 双指针版本
     * @param head
     * @return
     */
    public ListNode insertionSortList1(ListNode head) {
        if(head == null){
            return head;  //判空
        }

        ListNode pre = head;          //双指针维护前序
        ListNode cur = head.next;

        while(cur != null) {
            if (cur.val >= pre.val) {   //如果当前结点比上一个节点大，就没必要移动了，
                pre = pre.next;       //只需要移动一位当前节点和维护的前序节点。
                cur = cur.next;
                continue;
            }
            pre.next = cur.next;     // 不然的话，根据题意，把需要重定位的节点独立出来。
            // 需要给cur找个位置
            cur.next = null;

            if(cur.val < head.val){  //特殊处理，如果当前节点比head节点还小，那就直接插在头节点前，更新head
                cur.next = head;
                head = cur;
                cur = pre.next;
                continue;
            }

            ListNode c = head.next;   //如果以上既不比上一个节点大，又不比头节点小，那就是中间常规插入了。
            ListNode p = head;
            while(cur.val > c.val){   //找到合适的插入位置，p--->c    p--->  插这  --->c
                p = c;
                c = c.next;
            }

            cur.next = c;               //插入就ok了，记得更新cur
            p.next = cur;
            cur = pre.next;

        }
        return head;
    }

}
