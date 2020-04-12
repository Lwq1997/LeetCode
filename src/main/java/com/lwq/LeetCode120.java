package com.lwq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class LeetCode120 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();

        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);

        int res1 = minimumTotal(lists);
        int res2 = minimumTotal2(lists);
        System.out.println(res1);
        System.out.println(res2);
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        return helper(triangle.size(), 0, 0, triangle);
    }

    private static int helper(int row, int level, int c, List<List<Integer>> triangle) {
        if (level == row - 1) {
            // 到了最后一行，返回当前的值
            return triangle.get(level).get(c);
        }
        int left = helper(row, level + 1, c, triangle);
        int right = helper(row, level + 1, c + 1, triangle);
        return Math.min(left, right) + triangle.get(level).get(c);
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        HashMap<String, Integer> map = new HashMap<>();
        return helper2(map, triangle.size(), 0, 0, triangle);
    }

    private static int helper2(HashMap<String, Integer> map, int row, int level, int c, List<List<Integer>> triangle) {

        String key = level + "," + c;
        //这里一定要放一个有意义的符号，如果是空指，可能会出错
        if (map.get(key) != null) {
            return map.get(key);
        }
        if (level == row - 1) {
            // 到了最后一行，返回当前的值
            return triangle.get(level).get(c);
        }
        // 往左下节点走时
        int left = helper2(map, row, level + 1, c, triangle);
        // 往右下节点走时
        int right = helper2(map, row, level + 1, c + 1, triangle);

        int res = Math.min(left, right) + triangle.get(level).get(c);
        map.put(key, res);
        return res;
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 加1可以不用初始化最后一行
        // 根据题意，行列值相同
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + rows.get(j);
            }
        }
        return dp[0][0];
    }

}
