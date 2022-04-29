package com.lwq;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class LeetCode53 {
    public static void main(String[] args) {
        LeetCode53 leetCode53 = new LeetCode53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(leetCode53.maxSubArray(nums));
    }

    /**
     * - 一个连续子数组一定要以一个数作为结尾，那是不是可以定义**dp[i] 表示以 nums[i] 结尾的连续子数组的最大和**。
     * -  **dp[i] 所表示的连续子序列与 dp[i-1] 所表示的连续子序列很可能就差一个** **nums[i]，当然这是在dp[i-1]大于0的情况下**。
     * - dp[i] = dp[i-1]+nums[i] , if (dp[i-1] >= 0)
     * - **dp[i-1]也是有可能小于0的，此时dp[i]的值就是nums[i]了**。
     * - dp[i] = nums[i] , if (dp[i-1] < 0)
     * - 综上
     * - dp[i]=max(nums[i], dp[i−1]+nums[i])
     * - dp[0] = nums[0]
     * - 我们是寻找：max(dp[0], dp[1], ..., d[i-1], dp[i])
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
