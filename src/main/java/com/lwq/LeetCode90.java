package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class LeetCode90 {
    public static void main(String[] args) {
        subsetsWithDup(new int[]{1,2,2});
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，这样相同的两个元素必相邻
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        backTrace(res,tmp,nums,0);
        return res;
    }

    private static void backTrace(List<List<Integer>> res, ArrayList<Integer> tmp, int[] nums, int i) {
        res.add(new ArrayList<>(tmp));
        for(int j = i; j < nums.length ; j++){
            if(j > i && nums[j] == nums[j-1]){
                continue;
            }
            tmp.add(nums[j]);
            backTrace(res,tmp,nums,j+1);
            tmp.remove(tmp.size()-1);
        }
    }
}
