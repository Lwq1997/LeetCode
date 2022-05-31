package com.lwq.codecatalog.array;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode209 {
    public static void main(String[] args) {
        LeetCode209 leetCode209 = new LeetCode209();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println(leetCode209.minSubArrayLen01(target, nums));
        System.out.println(leetCode209.minSubArrayLen02(target, nums));
    }

    /**
     * 暴力解法
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen01(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    res = Math.min(res, j - i + 1);
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 滑动窗口
     *
     * 如果没到target，则移动窗口右边界，如果到了target，则移动窗口左边界
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen02(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0, sum = 0;

        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
