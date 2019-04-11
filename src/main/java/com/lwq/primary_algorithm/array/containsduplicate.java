package com.lwq.primary_algorithm.array;

import java.util.HashSet;

/**
 * @author Lwq
 * @create 2018-08-21 16:21
 * @desc 判断一个数组中是否存在重复元素
 **/
public class containsduplicate {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,1};
        System.out.println(containsDuplicate1(arr));
    }


    public static boolean containsDuplicate1(int[] nums) {
        if(nums==null||nums.length==0){
            return false;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = 0; i < nums.length ; i++){
            hashSet.add(nums[i]);
        }
        if(hashSet.size()!=nums.length){
            return true;
        }else {
            return false;
        }
    }
}
