package com.lwq.basic.array;

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
 *
 * 给出一个数组样例 [3,2,3,1,2], 返回 1
 */
public class maxProfit {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        int res = maxProfit(arr);
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
     * 动态规划，最大的卖，最小的买
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices){
        if(prices==null||prices.length==0){
            return 0;
        }
        int res = 0;
        int buy = prices[0];

        for(int i = 0;  i < prices.length;i++){
            res = Math.max(res,prices[i]-buy);
            buy = Math.min(buy,prices[i]);
        }
        return res;
    }

    /**
     * 差值法
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
