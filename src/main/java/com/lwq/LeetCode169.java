package com.lwq;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 *  
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 */
public class LeetCode169 {
    // 若记 众数 的票数为 +1 ，非众数 的票数为 -1 ，则一定有所有数字的 票数和 > 0
    public int majorityElement(int[] nums) {
        int x = 0;
        int votes = 0;
        for(int num:nums){
            if(votes == 0){
                x = num;
            }
            votes += x == num ? 1 : -1;
        }
        return x;
    }
}
