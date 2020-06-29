package com.lwq;

import java.util.*;

/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *  
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 */
public class LeetCode45_1 {
    public static void main(String[] args) {
        minNumber(new int[]{3,32,321});
    }
    public static String minNumber(int[] nums) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int num:nums)
            list.add(num);
        Collections.sort(list,new Comparator<Integer>(){
            public int compare(Integer num1,Integer num2){
                String str1 = num1+""+num2;
                String str2 = num2+""+num1;
                return str1.compareTo(str2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer.toString());
        }
        return sb.toString();
    }
}
