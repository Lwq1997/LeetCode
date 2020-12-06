package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * 通过次数136,480提交次数196,726
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode118 {
    /**
     * * 帕斯卡三角形
     *      *  1
     *      *  1 1
     *      *  1 2 1
     *      *  1 3 3 1
     *      *  1 4 6 4 1
     *      *  1 5 10 10 5 1
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> list = new ArrayList<>();

        int[][] arr = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> subList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
                subList.add(arr[i][j]);
            }
            list.add(subList);
        }
        return list;
    }
}