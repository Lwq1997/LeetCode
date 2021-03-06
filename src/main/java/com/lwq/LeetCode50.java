package com.lwq;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class LeetCode50 {
    public static void main(String[] args) {
        System.out.println(myPow(3, 11));
    }

    /**
     * 假设求a^b,按照朴素算法就是把a连乘b次，这样一来时间复杂度是O(n)级，快速幂能做到O(logn)
     * 首先把b写成它的二进制形式，假设b=11，11的二进制是1011，
     * 11 = 2³×1 + 2²×0 + 2¹×1 + 2º×1=2³+2¹+2º
     * 所以：a¹¹= a^2º * a ^2¹ * a ^ 2³ = a^1 * a^2 * a^8
     *
     * ans = 1
     * 因为1011的最后一位是1，所以ans = ans * x = 1 * 3^1 = 3
     * x = x * x = 9 = 3^2
     * 1011 ===》 0101(5)
     * 因为0101的最后一位是1，所以ans = ans * x = 3 * 3^2  = 27
     * x = x * x = 81 = 3^4
     * 0101 ===》 0010(2)
     * 因为0101的最后一位是0，所以ans不变
     * x = x * x = 6561 = 3^8
     * 0010 ===》 0001
     * 因为0101的最后一位是1，所以ans = ans * x = 27 * 3^8 = 177147
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        int temp_n = n;
        double ans = 1;
        while (n != 0) {
            if ((n & 1) != 0) {//判断n的最后一位是不是1
                ans *= x; //如果是0就不乘，让x养肥了再去乘
            }
            x *= x;
            n /= 2;//相当于二进制右移
        }
        return temp_n >= 0 ? ans : 1 / ans;

    }
}
