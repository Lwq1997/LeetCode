package com.lwq;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class LeetCode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        int len = candidates.length;

        // 排序是为了提前终止搜索
        Arrays.sort(candidates);

        dfs(candidates, len, target, 0, new ArrayDeque<>(), res);

        return res;
    }

    private void dfs(int[] candidates, int len, int target, int begin, ArrayDeque<Object> path, List<List<Integer>> res) {
        if(target == 0){
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            res.add(new ArrayList(path));
            return;
        }
        for(int i = begin; i < len; i++){
            // 在数组有序的前提下，剪枝.排序就可以加快速度
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            // 这里的i不能加一，因为数字可以重复使用
            dfs(candidates,len,target-candidates[i],i,path,res);
            path.removeLast();
        }
    }
}
