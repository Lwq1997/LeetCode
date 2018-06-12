package com.lwq;

import java.util.HashMap;

/**
 * @author Lwq
 * @create 2018-06-12 13:07
 * @desc    给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 **/
public class Two_Sum {
    //循环两次，每次把数组中的一个数和其他数加起来和target比较
    public int[] twoSum01(int[] nums,int target){
        for(int i = 0; i <nums.length ;i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public int[] twoSum02(int[] nums,int target){
        int len = nums.length;
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            map.put(nums[i],i);
        }
        for(int i = 0 ; i < nums.length ; i++){
            int k = target-nums[i];
            if(map.containsKey(k)&&map.get(k)!=i){
                result[0]=i;
                result[1]=map.get(k);
                break;
            }
        }
        return result;
    }

}
