package com.lwq;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class LeetCode77 {

    public static void main(String[] args) {
        combine(4, 2);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 0 || k < 0 || n < k) {
            return res;
        }
        Stack<Integer> path = new Stack<>();
        // 从 1 开始是题目的设定
        findCombinations(n, k, 1, path, res);
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
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历可能的搜索起点的最大值 : n
        // 开始剪枝
        //事实上，如果 n = 7, k = 4，从 5 开始搜索就已经没有意义了，这是因为：即使把 5 选上，后面的数只有 6 和 7，一共就 3 个候选数，凑不出 4 个数的组合。
        // 开始剪枝
        //        例如：n = 6 ，k = 4。
        //
        //        path.size() == 1 的时候，接下来要选择 3 个数，搜索起点最大是 4，最后一个被选的组合是 [4, 5, 6]；
        //        path.size() == 2 的时候，接下来要选择 2 个数，搜索起点最大是 5，最后一个被选的组合是 [5, 6]；
        //        path.size() == 3 的时候，接下来要选择 1 个数，搜索起点最大是 6，最后一个被选的组合是 [6]；
        //
        //        再如：n = 15 ，k = 4。
        //        path.size() == 1 的时候，接下来要选择 3 个数，搜索起点最大是 13，最后一个被选的是 [13, 14, 15]；
        //        path.size() == 2 的时候，接下来要选择 2 个数，搜索起点最大是 14，最后一个被选的是 [14, 15]；
        //        path.size() == 3 的时候，接下来要选择 1 个数，搜索起点最大是 15，最后一个被选的是 [15]；
        //
        //        可以归纳出：
        //
        //
        //        搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.add(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            findCombinations(n, k, i + 1, path, res);
            path.pop();
        }
    }
}
