package com.lwq;

/**
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * <p>
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 */
public class LeetCode507 {
    public static void main(String[] args) {
        System.out.println(checkPerfectNumber(496));
        System.out.println(checkPerfectNumber1(496));
    }

    /**
     * 1 是任意数字的因子，所以寻找其它因子的范围是 [2, sqrt(n)] 。
     * <p>
     * 为什么是到 sqrt(n) 呢？
     * <p>
     * 以数字 36 为例。
     * <p>
     * 它的非自身外正因子有，1、2、3、4、6、9、12、18，其中 1 和 6 单独计算，[2, 18]、[3, 12]、[4, 9] 都是对应关系，所以只需要遍历到 36 的平方根 6 就可以获取全部正因子。
     * <p>
     * 1单独计算的原因是要排除自身，6单独计算的原因是 6 * 6 = 36，两个值相同，故而只能计算一遍。
     *
     * @param num
     * @return
     */
    public static boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        int i = 2;
        double sqrt = Math.sqrt(num);
        for (; i < sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }
        if (i * i == num) {
            sum += i;
        }
        return sum == num;
    }

    /**
     * 欧几里得-欧拉定理告诉我们，每个偶完全数都可以写成 2^{p-1}(2^p-1)的形式，其中 p 为素数。例如前四个完全数可以写成如下形式：
     * <p>
     * 6 = 2^1 * (2^2 - 1)
     * 28 = 2^2 * (2^3 - 1)
     * 496 = 2^4 * (2^5 - 1)
     * 8128 = 2^6 * (2^7 - 1)
     * <p>
     * 目前奇完全数还未被发现
     *
     * @param num
     * @return
     */

    public static boolean checkPerfectNumber1(int num) {
        int[] primes = new int[]{2, 3, 5, 7, 13, 17, 19, 31};
        for (int prime : primes) {
            if (pn(prime) == num)
                return true;
        }
        return false;
    }

    public static int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

}
