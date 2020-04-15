package com.lwq;

/**
 *输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6]
 */
public class LeetCode57 {
    public static void main(String[] args) {

    }

    /**
     * 初始化： 双指针 ii , jj 分别指向数组 numsnums 的左右两端 （俗称对撞双指针）。
     * 循环搜索： 当双指针相遇时跳出；
         * 计算和 s = nums[i] + nums[j]s=nums[i]+nums[j] ；
         * 若 s > targets>target ，则指针 jj 向左移动，即执行 j = j - 1j=j−1 ；
         * 若 s < targets<target ，则指针 ii 向右移动，即执行 i = i + 1i=i+1 ；
         * 若 s = targets=target ，立即返回数组 [nums[i], nums[j]][nums[i],nums[j]] ；
     * 返回空数组，代表无和为 targettarget 的数字组合。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left<right){
            if(nums[left] + nums[right] > target){
                right--;
            }else if(nums[left] + nums[right] < target){
                left++;
            }else{
                return new int[]{nums[left],nums[right]};
            }
        }
        return new int[0];
    }
}
