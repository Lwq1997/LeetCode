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
    public static void main(String[] args) {
        int[] arr = {1,2,3,0,1,4,2,0};
        moveZeroes(arr);
        System.out.println(arr);
    }
    public static void moveZeroes(int[] nums) {
        int length = nums.length;
        int j = 0;   //不是0的个数
        for(int i = 0; i < length ; i++){
            if(nums[i]!=0){
                nums[j] = nums[i];
                j++;
            }
        }
        for(int i = j; i < length ;i++){
            nums[i] = 0;
        }
    }
}
