package com.lwq;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 */
public class LeetCode234 {
    public static void main(String[] args) {
        LeetCode234 leetCode234 = new LeetCode234();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        System.out.println(leetCode234.isPalindrome03(head));
    }

    ListNode back = null;

    public boolean isPalindrome(ListNode head) {
        back = head;
        return recursivelyCheck(head);
    }

    /**
     * 递归核心思想：back先不动，让head通过递归遍历到最后一个，然后back每次往后挪动一个，head因为是递归到最后的，反递归的时候就可以每次往前挪动一个
     *
     * @param head
     * @return
     */
    private boolean recursivelyCheck(ListNode head) {
        if (head != null) {
            if (!recursivelyCheck(head.next)) {
                return false;
            }
            if (head.next != back.next) {
                return false;
            }
            back = back.next;
        }
        return true;
    }


    public static boolean isPalindrome03(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode midNode = aaa(head);
        ListNode lastNode = reverse(midNode.next);
        ListNode p1 = head;
        ListNode p2 = lastNode;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        midNode.next = reverse(lastNode);
        return true;
    }

    private static ListNode reverse(ListNode midNode) {
        ListNode pre = null, cur = midNode;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private static ListNode aaa(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public boolean isPalindrome02(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    /**
     * 翻转中间节点之后的节点
     *
     * @param curr
     * @return
     */
    private ListNode reverseList(ListNode curr) {
        ListNode prev = null;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 通过快慢指针找到中间节点
     *
     * @param head
     * @return
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
