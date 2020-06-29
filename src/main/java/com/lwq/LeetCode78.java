package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class LeetCode78 {
    public static void main(String[] args) {
        subsets(new int[]{1,2,3});
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        backTrace(res,tmp,nums,0);
        return res;
    }

    private static void backTrace(List<List<Integer>> res, ArrayList<Integer> tmp, int[] nums, int i) {
        res.add(new ArrayList<>(tmp));
        for(int j = i; j < nums.length ; j++){
            tmp.add(nums[j]);
            backTrace(res,tmp,nums,j+1);
            tmp.remove(tmp.size()-1);
        }
    }
}
