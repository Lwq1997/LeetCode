package com.lwq;

/**
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class LeetCode70 {
    public static void main(String[] args) {
        LeetCode70 leetCode70 = new LeetCode70();
        System.out.println(leetCode70.climbStairs(3));
    }

    /**
     * “大问题由小问题的结果产生”的条件：
     *
     * - 上 1 阶台阶：有 1 种方式
     * - 上 2 阶台阶：有 1+1 和 2 两种方式
     * - 上 3 阶台阶：我们只能从第 2 阶或者第 1 阶 到达第 3 阶，所以到达第 3 阶的方法总数就是到第 1 阶和第 2 阶的方法数之和。
     * - 上 n 阶台阶：我们只能从第 n-1 阶或者第 n-2 阶 到达第 n 阶，所以到达第 n 阶的方法总数就是到第 n-1 阶和第 n-2 阶的方法数之和。
     *
     * - dp[n]=dp[n-1]+dp[n-2]
     * - 时间复杂度：O(n)，单循环到 n。
     * - 空间复杂度：O(n)，dp 数组用了n 的空间。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
