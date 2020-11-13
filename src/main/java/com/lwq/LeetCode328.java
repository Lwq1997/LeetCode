package com.lwq;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode328 {
    /**
     * odd 指针扫描奇数节点，even 指针扫描偶数节点
     * odd 指针将奇数节点们，通过 next 连成奇链
     * even 指针将偶数节点们，通过 next 连成偶链
     * <p>
     * 扫描做四件事：
     * 当前奇数节点 ——next——> 下一个奇数节点
     * odd 指针推进 ——————> 下一个奇数节点
     * 当前偶数节点 ——next——> 下一个偶数节点
     * even 指针推进 ——————> 下一个偶数节点
     * <p>
     * 我们希望，扫描结束时，odd 指向最后一个奇数节点
     * 奇链的尾节点——next——>偶链的头结点，前奇后偶。
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        // odd 指针扫描奇数节点
        ListNode odd = head;
        // even 指针扫描偶数节点
        ListNode even = head.next;
        // 保存偶数节点的头结点，为了最后衔接
        ListNode even_head = even;
        while (even != null && even.next != null) {
            // even.next是下一个奇数节点,赋值给odd.next
            odd.next = even.next;
            // odd 推进到下一个奇数节点，此时这一步奇数处理完了
            odd = odd.next;
            // odd.next.next是下一个偶数节点,赋值给even.next
            even.next = odd.next;
            // even 推进到下一个偶数节点
            even = even.next;
        }
        odd.next = even_head;
        return head;
    }
}
