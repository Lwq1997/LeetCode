package com.lwq;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class LeetCode312 {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));
    }

    static int maxCoins = 0;

    public static int maxCoins(int[] nums) {
        maxCoins2(nums, 0, nums.length, 0);
        return maxCoins;
    }

    /**
     * @Param nums:气球数组，
     * level：递归层级，即currentLevel,
     * length：数组长度，防止每层都计算一次，
     * beforeCoins：之前所有层获得的金币和，即currentCoin
     * @Return
     * @Exception
     * @Description 回溯解法
     */
    public static void maxCoins2(int[] nums, int level, int length, int beforeCoins) {
        if (level == length) {
            if (beforeCoins > maxCoins) {
                maxCoins = beforeCoins;
            }
            return;
        }

        for (int i = 0; i < length; i++) {
            // 标记已经戳破的气球
            int temp = nums[i];
            nums[i] = -1;
            //获取上一个气球的数字
            int before = i - 1;
            int beforeNum = 0;
            //往前遍历找一个没有被戳破的气球
            while (before > -1 && nums[before] == -1) {
                before--;
            }
            // 如果没有上一个气球，上一个就默认为1
            if (before < 0) {
                beforeNum = 1;
            } else {
                // 如果有上一个，就用上一个气球的数
                beforeNum = nums[before];
            }

            //获取下一个气球的数字
            int next = i + 1;
            int nextNum = 0;
            //往后遍历找一个没有被戳破的气球
            while (next < length && nums[next] == -1) {
                next++;
            }
            if (next > length - 1) {
                nextNum = 1;
            } else {
                nextNum = nums[next];
            }

            //计算戳破当前气球的coin
            int tempCoin = temp * nextNum * beforeNum;
            //递归进行下一戳
            maxCoins2(nums, level + 1, length, beforeCoins + tempCoin);
            //回溯尝试其它戳法
            nums[i] = temp;
        }
    }

    public static int maxCoins1(int[] nums) {
        int n = nums.length;

        int[] points = new int[n + 2];
        // 添加两侧的虚拟气球
        points[0] = 1;
        points[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
           points[i] = nums[i-1];
        }

        //// base case 已经都被初始化为 0
        int[][] dp = new int[n+2][n+2];
        // i 应该从下往上
        for (int i = n; i >= 0; i--) {
            // j 应该从左往右
            for (int j = i + 1; j < n + 2; j++) {
                // 最后戳破的气球是哪个？
                for (int k = i + 1; k < j; k++) {
                    // 择优做选择
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]
                    );
                }
            }
        }
        return dp[0][n+1];
    }
}
