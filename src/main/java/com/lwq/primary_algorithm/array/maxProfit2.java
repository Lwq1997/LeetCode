package com.lwq.primary_algorithm.array;

/**
 * @author lwq
 * @create 2018-08-20 13:14
 * @desc 买卖股票的最佳时机2
 **/
/**
 *假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 交易次数不限，但一次只能交易一支股票，也就是说手上最多只能持有一支股票，求最大收益。
 */
/**
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 */
/**
 * 当明天的价格比今天的价格贵的时候我们今天买，明天卖，这样能够获取最大利润。
 */
public class maxProfit2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int res = maxProfit(arr);
        System.out.println(res);
    }

    public static int maxProfit(int[] prices) {
        if(prices.length==0||prices==null){
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length - 1; ++i)
            if (prices[i] < prices[i + 1])
                max += prices[i + 1] - prices[i];
        return max;
    }
}
