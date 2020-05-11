package com.lwq;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000

 */
public class LeetCode06_1 {
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop().val;
        }
        return res;
    }

    ArrayList<Integer> arrayList=new ArrayList<Integer>();
    public ArrayList<Integer> reversePrint1(ListNode listNode) {
        if(listNode!=null){
            this.reversePrint1(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}
