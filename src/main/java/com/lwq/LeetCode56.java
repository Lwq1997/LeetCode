package com.lwq;

import java.util.Arrays;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class LeetCode56 {
    public static void main(String[] args) {
        merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        //[[1,6],[8,10],[15,18]]
    }

    public static int[][] merge(int[][] intervals) {
        // 排序原有的数组
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);

        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            //如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，则不合并，直接将当前区间加入结果数组。
            if (index == -1 || res[index][1] < interval[0]) {
                res[++index] = interval;
            } else {
                // 合并
                res[index][1] = Math.max(res[index][1], interval[1]);
            }

        }
        return Arrays.copyOf(res, index + 1);

    }
}
