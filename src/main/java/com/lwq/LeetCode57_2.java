package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 */
public class LeetCode57_2 {
    public static void main(String[] args) {
        findContinuousSequence(9);
    }

    /**
     * 使用滑动窗口
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        List<int[]> ress = new ArrayList<>();
        int left = 1;
        int right = 1;
        int window = 0;
        while (left <= target / 2) {
            // 上面要写<=.如果targer = 9,target/2 = 4。会有4，5的结果
            if (window < target) {
                //小了，移动右边界
                window += right;
                right++;
            } else if (window > target) {
                //大了，移动左边界
                window -= left;
                left++;
            } else {
                int size = right-left;
                int[] res = new int[size];
                for(int k = left; k < right;k++){
                    //上面要写<right。target = 9,left = 4,right = 6。res[0] = 4,res[1] = 5
                    res[k-left] = k;
                }
                ress.add(res);
                window -= left;
                left++;
            }
        }
        return ress.toArray(new int[ress.size()][]);
    }
}