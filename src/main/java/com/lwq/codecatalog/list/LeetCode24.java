package com.lwq.codecatalog.list;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode24 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode listNode1 = swapPairs02(listNode);
    }
    /**
     * 递归版本
     * @param head
     * @return
     */
    public static ListNode swapPairs01(ListNode head) {
        if(head == null || head.next==null){
            return head;
        }
        ListNode next = head.next;
        // 进行递归
        ListNode newNode = swapPairs01(next.next);
        // 这里进行交换，仅仅需要交换两个节点
        next.next = head;
        head.next = newNode;

        //这里返回两个节点中的头结点，也就是next
        return next;
    }

    /**
     * 虚拟头节点版本
     * @param head
     * @return
     */
    public static ListNode swapPairs02(ListNode head) {
        ListNode dummy = new ListNode(0,head);
        ListNode pre = dummy;
        while(pre.next!=null && pre.next.next!=null) {
            //说明有两个可以翻转的节点存在
            ListNode temp = head.next.next; // 先把下一次翻转的节点存下来，以防丢失

            pre.next = head.next; //步骤一
            head.next.next = head; // 步骤二
            head.next = temp;  // 步骤三
            pre = head;
            head = head.next;
        }
        return dummy.next;
    }
}
