package com.lwq;

import java.util.HashMap;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *  
 *
 * 提示：
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class LeetCode494 {
    int res = 0;
    /**
     * 使用备忘录来消除重复的计算
     */
    HashMap<String, Integer> map = new HashMap<>();

    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }
        backtrack(nums, 0, S);
        return res;
    }

    private void backtrack(int[] nums, int i, int target) {
        if (i == nums.length) {
            if (target == 0) {
                res++;
            }
            return;
        }
        target += nums[i];
        backtrack(nums, i + 1, target);
        target -= nums[i];


        target -= nums[i];
        backtrack(nums, i + 1, target);
        target += nums[i];
    }

    public int findTargetSumWays2(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }
        return dp(nums, 0, S);
    }

    private int dp(int[] nums, int i, int target) {
        if (i == nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        String key = i + "," + target;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int res = dp(nums, i + 1, target + nums[i]) + dp(nums, i + 1, target - nums[i]);
        map.put(key, res);
        return res;
    }

    /**
     * 动态规划
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays3(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        return getRes(nums, (sum + S) / 2);
    }

    private int getRes(int[] nums, int sum) {
        int[][] dp = new int[nums.length+1][sum+1];
        // base case
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = 0; i <= nums.length; i++){
            for(int j = 0; j <= sum; j++){
                if (j >= nums[i-1]) {
                    // 两种选择的结果之和
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                } else {
                    // 背包的空间不足，只能选择不装物品 i
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][sum];
    }
}
