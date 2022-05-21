package com.lwq;

import java.util.List;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * <p>
 * <p>
 * <p>
 * 在节点 c1 开始相交。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class LeetCode160 {
    public static void main(String[] args) {
        //测试用例

        new LeetCode160().getIntersectionNode(null, null);
    }

    /**
     * 下面展示的是两个无环链表相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode h1 = headA;
        ListNode h2 = headB;
        // A和B两个链表长度可能不同，但是A+B和B+A的长度是相同的
        // 所以遍历A+B和遍历B+A一定是同时结束。
        // 如果A,B相交的话A和B有一段尾巴是相同的，所以两个遍历的指针一定会同时到达交点
        // 如果A,B不相交的话两个指针就会同时到达A+B（B+A）的尾节点
        while (h1 != h2) {
            h1 = h1 == null ? headB : h1.next;
            h2 = h2 == null ? headA : h2.next;
        }
        return h1;
    }


    /**
     * 下面展示的是两个无环链表相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode01(ListNode headA, ListNode headB) {
        //说明环形在两个链表相交之后出现，所以环形的公共的
        ListNode node1 = headA;
        ListNode node2 = headB;
        int n = 0;
        while (node1 != null) {
            n++;
            node1 = node1.next;
        }
        while (node2 != null) {
            n--;
            node2 = node2.next;
        }
        //node1赋值给比较长的那个链表的头结点
        node1 = n > 0 ? headA : headB;
        node2 = headA == node1 ? headB : headA;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            node1 = node1.next;
        }
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }


    /**
     * 下面展示两个有环链表相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode02(ListNode headA, ListNode headB) {
        //找到headA链表的入环节点
        ListNode loopA = detectCycle(headA);
        ListNode loopB = detectCycle(headB);
        if (loopA == null && loopB == null) {
            //无环链表相交问题
//            return getIntersectionNode(headA, headB);
            //无环链表相交问题
            return getIntersectionNode01(headA, headB);
        }
        if (loopA != null && loopB != null) {
            //有环链表相交问题
            return bothloop(headA, loopA, headB, loopB);
        }
        return null;
    }

    private static ListNode bothloop(ListNode headA, ListNode loopA, ListNode headB, ListNode loopB) {
        ListNode node1 = null, node2 = null;
        if (loopA == loopB) {
            //说明环形在两个链表相交之后出现，所以环形的公共的。所以往下寻找的时候需要截止到入环口，否则会死循环
            node1 = headA;
            node2 = headB;
            int n = 0;
            while (node1 != loopA) {
                n++;
                node1 = node1.next;
            }
            while (node2 != loopB) {
                n--;
                node2 = node2.next;
            }
            //node1赋值给比较长的那个链表的头结点
            node1 = n > 0 ? headA : headB;
            node2 = headA == node1 ? headB : headA;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                node1 = node1.next;
            }
            while (node1 != node2) {
                node1 = node1.next;
                node2 = node2.next;
            }
            return node1;
        } else {
            //此时如果还相交，说明环是公共的，但是入环节点不一样，此时把节点node1放到A入环口,node1开始往下走（走一圈即可），如果遇到了链表B的入环口，则直接返回
            node1 = loopA.next;
            while (node1 != loopA) {
                if (node1 == loopB) {
                    return node1;
                }
                node1 = node1.next;
            }
            return null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
