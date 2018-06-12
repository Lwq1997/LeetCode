package com.lwq;

/**
 * @author Lwq
 * @create 2018-06-12 19:22
 * @desc  给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，
 * 它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 *
 **/
public class Add_Two_Numbers {
     class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode first,ListNode second){
         if(first==null){
             return second;
         }
         if(second==null){
             return first;
         }
         ListNode head = new ListNode(0);
         ListNode cur = head;
         int sum = 0;
         int carry = 0;
         while(first!=null||second!=null||carry!=0){
             int num1 = 0;
             int num2 = 0;
             if(first!=null){
                 num1 = first.val;
                 first=first.next;
             }
             if(second!=null){
                 num2 = second.val;
                 second = second.next;
             }
             sum = (num1+num2+carry)%10;
             ListNode temp = new ListNode(sum);
             cur.next = temp;
             cur = cur.next;
             carry = (num1+num2+carry)/10;
         }
         return head.next;
    }

}
