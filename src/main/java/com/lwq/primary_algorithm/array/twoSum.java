package com.lwq.primary_algorithm.array;

import java.util.HashMap;

/**
 * @author Lwq
 * @create 2018-06-12 13:07
 * @desc    给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 **/
public class twoSum {
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        twoSum(arr,9);
        int a = 0;
    }

    public static int[] twoSum(int[] nums,int target){
        int len = nums.length;
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            int anotherNum = target-nums[i];
            if(map.containsKey(anotherNum)){
                result[0]=map.get(anotherNum);
                result[1]=i;
            }
            map.put(nums[i],i);
        }
        return result;
    }

}
