package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode57_1 {
    public static void main(String[] args) {
        insert(new int[][]{{1,3},{6,9}},new int[]{2,5});
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        //先合并到一起
        int[][] temp = new int[intervals.length+1][2];
        int index = 0;
        for (int[] interval : intervals) {
            temp[index++] = interval;
        }
        temp[index++] = newInterval;

        // 排序原有的数组
        Arrays.sort(temp, (v1, v2) -> v1[0] - v2[0]);

        int[][] res = new int[temp.length+1][2];
        index = -1;
        for (int[] interval : temp) {
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

    /**
     * 不重叠，需满足：绿区间的右端，位于蓝区间的左端的左边，如[1,2]。
     *       则当前绿区间，推入 res 数组，指针 +1，考察下一个绿区间。
     *       循环结束时，当前绿区间的屁股，就落在蓝区间之前，有重叠了，如[3,5]。
     *
     * 现在看重叠的。我们反过来想，没重叠，就要满足：绿区间的左端，落在蓝区间的屁股的后面，反之就有重叠：绿区间的左端 <= 蓝区间的右端，极端的例子就是[8,10]。
     *       和蓝有重叠的区间，会合并成一个区间：左端取蓝绿左端的较小者，右端取蓝绿右端的较大者，不断更新给蓝区间。
     *       循环结束时，将蓝区间（它是合并后的新区间）推入 res 数组。
     *
     * 剩下的，都在蓝区间右边，不重叠。不用额外判断，依次推入 res 数组。
     *
     * 作者：xiao_ben_zhu
     * 链接：https://leetcode-cn.com/problems/insert-interval/solution/shou-hua-tu-jie-57-cha-ru-qu-jian-fen-cheng-3ge-ji/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        int len = intervals.length;
        int i = 0;
        // 判断左边不重合
        while (i < len && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }
        // 判断重合
        while (i < len && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(newInterval);
        // 判断右边不重合
        while (i < len && intervals[i][0] > newInterval[1]) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
