package com.lwq.primary_algorithm.array;

/**
 * @Author: Lwq
 * @Date: 2018/8/24 16:06
 * @Version 1.0
 * @Describe
 */

/**
 *  给定一个数组 nums，
 *  编写一个函数将所有 0 移动到数组的末尾，
 *  同时保持非零元素的相对顺序。
 *
 *  必须在原数组上操作，不能拷贝额外的数组。
 */
public class movezeroes {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast<nums.length){
            if(nums[fast]!=0){
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }else {
                fast++;
            }
        }
        for(int i = slow;i<nums.length;i++){
            nums[i] = 0;
        }
    }
}
