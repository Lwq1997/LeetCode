package com.lwq;

/**
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 注意这五个元素可为任意顺序。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 */
public class LeetCode27 {
    public static void main(String[] args) {
        int[] nums = {0,1,2};
        int res = removeElement(nums,3);
        System.out.println(res);
    }


    public static int removeElement(int[] nums, int val) {
        if(nums.length == 0){
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length ;j++){
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }
}

