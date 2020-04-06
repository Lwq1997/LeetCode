package com.lwq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class LeetCode16 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2};
        int target = 3;
        System.out.println(threeSumClosest(nums, target));
    }

    /**
     * - 标签：排序和双指针
     * - 本题目因为要计算三个数，如果靠暴力枚举的话时间复杂度会到O(n^3)，需要降低时间复杂度
     * - 首先进行数组排序，时间复杂度O(nlogn)
     * - 在数组nums中，进行遍历，每遍历一个值利用其下标i，形成一个固定值nums[i]
     * - 再使用前指针指向`start = i + 1`处，后指针指向`end = nums.length - 1`处，也就是结尾处
     * - 根据 `sum = nums[i] + nums[start] + nums[end]` 的结果，判断sum与目标target的距离，如果更近则更新结果ans
     * - 同时判断sum与target的大小关系，因为数组有序，如果`sum > target` 则 `end--`，如果`sum < target` 则 `start++`，如果`sum == target` 则说明距离为0直接返回结果
     * - 整个遍历过程，固定值为n次，双指针为n次，时间复杂度为O(n^2)
     * - 总时间复杂度：O(nlogn) + O(n^2) = O(n^2)
     *
     * @param nums
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //初始化这个结果
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                }
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    //如果sum = target .则res = target .这时候left和right都不会变需要返回结果，否则会死循环
                    return res;
                }
            }
        }
        return res;
    }

}