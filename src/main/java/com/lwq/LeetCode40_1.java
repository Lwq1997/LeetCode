package com.lwq;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
 */
public class LeetCode40_1 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            // 小剪枝，对应上图gua
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            // 这里的i需要加一，因为数字不重复使用
            dfs(candidates,len,target-candidates[i],i+1,path,res);
            path.removeLast();
        }
    }
}
