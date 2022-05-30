package com.lwq.codecatalog;

/**
 * X的N次方
 * https://programmercarl.com/%E5%89%8D%E5%BA%8F/%E9%80%9A%E8%BF%87%E4%B8%80%E9%81%93%E9%9D%A2%E8%AF%95%E9%A2%98%E7%9B%AE%EF%BC%8C%E8%AE%B2%E4%B8%80%E8%AE%B2%E9%80%92%E5%BD%92%E7%AE%97%E6%B3%95%E7%9A%84%E6%97%B6%E9%97%B4%E5%A4%8D%E6%9D%82%E5%BA%A6%EF%BC%81.html
 */
public class XToTheN {
    public static void main(String[] args) {
        XToTheN xToTheN = new XToTheN();
        System.out.println(xToTheN.function1(2d, 3));
        System.out.println(xToTheN.function2(2d, 3));
        System.out.println(xToTheN.function3(2d, 3));
        System.out.println(xToTheN.function4(2d, 3));
    }
    /**
     * 复杂度：O(N)
     *
     * @param x: An integer
     * @param n: An integer
     * @return: The result
     */
    public double function1(Double x, int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }

    /**
     * 复杂度：O(N)
     *
     * @param x
     * @param n
     * @return
     */
    public double function2(Double x, int n) {
        if (n == 0) {
            return 1d;
        }
        return function2(x, n - 1) * x;
    }

    /**
     * 复杂度：O(N)
     *
     * @param x
     * @param n
     * @return
     */
    public double function3(Double x, int n) {
        if (n == 0) {
            return 1d;
        }
        if (n % 2 == 0) {
            //如果N是偶数，则返回X的N/2次方
            return function3(x, n / 2) * function3(x, n / 2);
        }
        return function3(x, n / 2) * function3(x, n / 2) * x;
    }

    /**
     * 复杂度：O(logN)
     *
     * @param x
     * @param n
     * @return
     */
    public double function4(Double x, int n) {
        if (n == 0) {
            return 1d;
        }
        double temp = function4(x, n / 2);
        if (n % 2 == 0) {
            //如果N是偶数，则返回X的N/2次方
            return temp * temp;
        }
        return temp * temp * x;
    }
}
