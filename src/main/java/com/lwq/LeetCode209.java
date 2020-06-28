package com.lwq;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例: 
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class LeetCode209 {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen1(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    /**
     * 枚举数组 nums 中的每个下标作为子数组的开始下标，对于每个开始下标 i，
     * 需要找到大于或等于 i 的最小下标 j，使得从 nums[i] 到 nums[j] 的元素和大于或等于 s，
     * 并更新子数组的最小长度（此时子数组的长度是 j-i+1）。
     *
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int target = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            target = 0;
            for (j = i; j < nums.length; j++) {
                target += nums[j];
                if (target >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static int minSubArrayLen1(int s, int[] nums) {
        int len = nums.length;
        if (len == 0 || nums == null) {
            return 0;
        }
        int left = 0, right = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        while (right < len) {
            sum += nums[right];
            while (sum >= s) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
