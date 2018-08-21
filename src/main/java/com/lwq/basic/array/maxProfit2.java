package com.lwq.basic.array;

/**
 * @author lwq
 * @create 2018-08-20 13:14
 * @desc 买卖股票的最佳时机2
 **/

import java.util.ArrayList;
import java.util.List;

/**
 *假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 交易次数不限，但一次只能交易一支股票，也就是说手上最多只能持有一支股票，求最大收益。
 * 给出一个数组样例 输入: [7,1,5,3,6,4]
 * 输出: 7
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
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < prices.length-1 ; i++){
            list.add(prices[i+1]-prices[i]);
        }
        int max = 0;
        for(Integer i:list){
            if(i>0){
                max+=i;
            }
        }
        return max;
    }
}
