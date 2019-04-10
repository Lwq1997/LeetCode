package com.lwq.primary_algorithm.array;

/**
 * @author lwq
 * @create 2018-08-20 13:14
 * @desc 买卖股票的最佳时机1
 **/

import java.util.ArrayList;
import java.util.List;

/**
 *假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。
 */

/**
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 */

/**
 * 先计算每两天之间的利润，得到一个n-1的数组，然后计算最大子数组的和
 */
public class maxProfit {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        int res = maxProfit2(arr);
        System.out.println(res);
    }

    /**
     * 暴力解法O（n^2）
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices){
        if(prices==null||prices.length==0){
            return 0;
        }
        int max = 0;
        for(int i = 0; i < prices.length ; i++){
            for(int j = i; j <prices.length ; j++){
                max = Math.max(max,prices[j]-prices[i]);
            }
        }
        return max;
    }

    /**
     * 计算整个数组中最大值和最小值的差
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices){
        if(prices==null||prices.length==0){
            return 0;
        }
        int maxprofit = 0;
        int minprice = prices[0];
        for(int i = 0; i < prices.length;i++){
            minprice = Math.min(minprice,prices[i]);
            maxprofit = Math.max(maxprofit,prices[i]-minprice);
        }
        return maxprofit;
    }

    /**
     * 差值法(求连续子数组的最大和)
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices){
        if(prices==null||prices.length==0){
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < prices.length-1 ; i++){
            list.add(prices[i+1]-prices[i]);
        }
        int mid = 0;
        int res = 0;
        for(int i:list){
            if(mid+i>0){
                mid+=i;
            }else {
                mid = 0;
            }
            res = Math.max(mid,res);
        }
        return res;
    }
}
