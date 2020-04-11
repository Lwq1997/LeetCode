package com.lwq;

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 * 示例 2：
 *
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
 * 示例 3：
 *
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 *  
 *
 * 提示：
 *
 * 0 ≤ N ≤ 30
 *
 */
public class LeetCode509 {
    public static void main(String[] args) {
        System.out.println(fib(20));
    }

    public static int fib(int N) {
        if (N <= 1) {
            return N;
        }
        return memoize(N);
    }

    private static int memoize(int n) {
        int[] cache = new int[n+1];
        cache[1] = 1;
        cache[2] = 1;
        for(int i = 3; i <= n ; i++){
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[n];
    }

    private static int memoize2(int n) {
        int pre = 1;
        int curr = 1;
        for(int i = 2; i <= n; i++){
            int sum = pre+curr;
            pre = curr;
            curr = sum;
        }
        return curr;
    }
}
