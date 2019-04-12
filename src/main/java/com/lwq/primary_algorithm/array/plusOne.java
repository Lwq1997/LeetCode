package com.lwq.primary_algorithm.array;

/**
 * @Author: Lwq
 * @Date: 2018/8/24 15:39
 * @Version 1.0
 * @Describe
 */


/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 */
public class plusOne {
    public static void main(String[] args) {
        int[] digits = {9,9,9,9};
        plusOne(digits);
    }
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        for(int i = length-1 ; i >= 0;i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //万一全是9
        int[] result = new int[length+1];
        result[0] = 1;
        return result;
    }
}
