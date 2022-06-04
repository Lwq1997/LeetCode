package com.lwq.codecatalog.list;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-linked-list-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode203 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(6);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(5);
        LeetCode203 leetCode203 = new LeetCode203();
        ListNode listNode1 = leetCode203.removeElements01(listNode, 1);
        ListNode listNode2 = leetCode203.removeElements02(listNode, 1);
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
        System.out.println("==========================");
        while (listNode2 != null) {
            System.out.println(listNode2.val);
            listNode2 = listNode2.next;
        }
    }

    /**
     * 虚拟节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements01(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 因为删除可能涉及到头节点，所以设置dummy节点，统一操作
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                //说明需要删除cur节点
                pre.next = next;
            } else {
                pre = cur;
            }
            cur = next;
        }
        return dummy.next;
    }

    /**
     * 无虚拟节点
     *
     * @return
     */
    public ListNode removeElements02(ListNode head, int val) {
        while (head != null && head.val == val) {
            //如果要删除头节点，则直接下移即可
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                //说明需要删除cur节点
                pre.next = next;
            } else {
                pre = cur;
            }
            cur = next;
        }
        return head;
    }
}
