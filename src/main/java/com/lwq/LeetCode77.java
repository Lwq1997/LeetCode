package com.lwq;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class LeetCode77 {

    public static void main(String[] args) {
        combine(4,2);
    }
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < 0 || k < 0 || n < k){
            return res;
        }
        Stack<Integer> path = new Stack<>();
        findCombinations(n, k, 1, path,res);
        return res;
    }

    /**
     * 当选定了一个元素，即 pre.size() == 1 的时候，接下来要选择 2 个元素， i 最大的值是 4 ，因为从 5 开始选择，就无解了；
     * 当选定了两个元素，即 pre.size() == 2 的时候，接下来要选择 1 个元素， i 最大的值是 5 ，因为从 6 开始选择，就无解了。
     *
     * @param n
     * @param k
     * @param begin
     * @param path
     * @param res
     */
    private static void findCombinations(int n, int k, int begin, Stack<Integer> path, List<List<Integer>> res) {
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin; i <= n - (k - path.size()) + 1; i++){
            path.add(i);
            findCombinations(n,k,i+1,path,res);
            path.pop();
        }
    }
}
