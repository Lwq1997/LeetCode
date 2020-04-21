package com.lwq;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LeetCode300 {
    public static void main(String[] args) {

    }

    /**
     * dp[i] 表示以nums[i]结尾的最长上升子序列的长度。
     *
     * - 如果nums[i]比前面的所有元素都小，那么dp[i]等于1（即它本身）（该结论正确）
     * - 如果nums[i]前面存在比他小的元素nums[j]，那么dp[i]不一定等于dp[j]+1，而是需要找到所有存在比i位置小的数据比如k，p等。需要找到dp[j]+1，dp[k]+1，dp[p]+1 中的最大值。
     * - **dp[i] = max(dp[j]+1，dp[k]+1，dp[p]+1，.....)**
     *   - nums[i] > nums[j]
     *   - nums[i] > nums[k]
     *   - nums[i] > nums[p]
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        int res = 1;
        //初始化dp数组，默认前面都是比他大的数据，所以初始化为1
        Arrays.fill(dp, 1);
        for(int i = 0;i < len; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
