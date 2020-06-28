package com.lwq;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * <p>
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：6
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= n < 2^31
 */
public class LeetCode43_1 {
    public int countDigitOne(int n) {
        // 初始化
        // 十位到最高位
        int high = n / 10;
        // 个位
        int cur = n % 10;
        int low = 0;
        int digit = 1;

        int res = 0;

        while (high != 0 || cur != 0) {
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }

            //# 当 high 和 cur 同时为 0 时，说明已经越过最高位，因此跳出
            low += cur * digit; // 将 cur 加入 low ，组成下轮 low
            cur = high % 10; // 下轮 cur 是本轮 high 的最低位
            high /= 10; // 将本轮 high 最低位删除，得到下轮 high
            digit *= 10; // 位因子每轮 × 10
        }
        return res;
    }

}
