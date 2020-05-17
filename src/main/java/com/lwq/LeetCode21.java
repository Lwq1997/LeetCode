package com.lwq;

import org.junit.Test;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */
public class LeetCode21 {
    @Test
    public void exchange(int[] nums) {
        int slow = 0,fast = 0;
        while (fast<nums.length){
            if((nums[fast] & 1) == 0){
                slow++;
            }
            fast++;
        }
    }

    private void swap(int[] nums,int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] =temp;
    }
}
