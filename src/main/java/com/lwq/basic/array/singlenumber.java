package com.lwq.basic.array;

/**
 * @Author: Lwq
 * @Date: 2018/8/24 14:24
 * @Version 1.0
 * @Describe
 */

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，
 * 其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 不使用额外空间
 *
 * 一个数异或自身是0,0异或任何数都是那个数
 */
public class singlenumber {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {
        if(nums.length==0||nums==null){
            return 0;
        }
        int num = 0;
        for(int i = 0; i < nums.length ; i++){
           num^=nums[i];
        }
        return num;
    }
}
