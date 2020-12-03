package com.lwq;

public class LeetCode204 {
    /**
     * 暴力解法
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }

    /**
     * 一切非 2 偶数一定不可能为质数。所以，我们可以在此处进行另一步的优化。
     *
     * @param n
     * @return
     */
    public int countPrimes1(int n) {
        if (n < 3) {
            return 0;
        }
        //从3开始验算，所以初始值为1（2为质数）。
        int count = 1;
        for (int i = 3; i < n; i++) {
            //当某个数为 2 的 n 次方时（n为自然数），其 & (n - 1) 所得值将等价于取余运算所得值
            //*如果 x = 2^n ，则 x & (n - 1) == x % n
            //if(i % 2 == 0) == i & 1
            if ((i & 1) == 0) {
                // 说明是偶数
                continue;
            }
            boolean flag = true;
            for (int j = 3; j * j <= i; j += 2) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }

    /**
     * 从2出发，每次排除当前数的倍数
     *
     * @param n
     * @return
     */
    public int countPrimes2(int n) {
        boolean[] bools = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!bools[i]) {
                // 说明当前数是质数
                count++;
                // 然后把该数的所有倍数都置为true，那些数肯定不是质数
                for (int j = i + i; j < n; j += i) {
                    //排除不是质数的数
                    bools[j] = true;
                }
            }
        }
        return count;
    }
}


