package com.lwq.codecatalog.list;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode206 {
    /**
     * 翻转链表，
     *
     * @param head
     * @return
     */
    public ListNode reverseList01(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            // 保存一下 cur的下一个节点，因为接下来要改变cur->next
            ListNode next = cur.next;
            cur.next = pre;// 翻转操作
            // 更新pre 和 cur指针
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归翻转
     *
     * @param head
     * @return
     */
    public ListNode reverseList02(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode pre, ListNode head) {
        if (head == null) {
            return pre;
        }
        ListNode next = head.next;//保存下一个节点
        head.next = pre;//翻转
        // 更新prev、cur位置
        // prev = cur;
        // cur = temp;
        return reverse(head, next);
    }
}
