package com.lwq;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 测试用例的答案是一个 32-位 整数。
 * <p>
 * 子数组 是数组的连续子序列。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode152 {
    public static void main(String[] args) {
        LeetCode152 leetCode152 = new LeetCode152();
        int[] nums = {2, 3, -2, 4};
        System.out.println(leetCode152.maxProduct01(nums));
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                //如果当前是一个负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    public int maxProduct01(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int res =  nums[0];
        int length = nums.length;
        int[] dp_max = new int[length];
        int[] dp_min = new int[length];

        //初始化DP；
        dp_max[0] = nums[0]; dp_min[0] = nums[0];

        for (int i = 1; i < length; i++) {
            /*
            考虑当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。
            根据当前数 nums[i] 和前面累积的结果 dp_max[i-1]、dp_min[i-1] 的「正负」进行分类讨论

            若当前数 nums[i] 为正
            dp_max[i] = max(dp_max[i-1] * nums[i], nums[i])
            dp_min[i] = min(dp_min[i-1] * nums[i], nums[i])
            若当前数 nums[i] 为负
            dp_max[i] = max(dp_min[i-1] * nums[i], nums[i])
            dp_min[i] = min(dp_max[i-1] * nums[i], nums[i])

             */
            if (nums[i] >= 0) {
                dp_max[i] = Math.max(dp_max[i - 1] * nums[i], nums[i]);
                dp_min[i] = Math.min(dp_min[i - 1] * nums[i], nums[i]);
            } else if (nums[i] < 0) {
                dp_max[i] = Math.max(dp_min[i - 1] * nums[i], nums[i]);
                dp_min[i] = Math.min(dp_max[i - 1] * nums[i], nums[i]);
            }
            res = Math.max(res, dp_max[i]);
        }
        return res;
    }
}
