package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class LeetCode46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = permute(nums);
        for (List<Integer> re : res) {
            for (Integer integer : re) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        ArrayList<Integer> path = new ArrayList<>();

        backtrack(res, nums, path, used);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            //这里如果不new，path的引用会一直改变
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //数显判断当前位置的这个数字是否被使用过
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                backtrack(res, nums, path, used);
                //这里是状态重制
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
