package com.lwq;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class LeetCode54 {
    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] seen = new boolean[row][col];

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        // r, c 表示当前下标
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < row * col; i++) {
            res.add(matrix[r][c]);
            seen[r][c] = true;
            // cr和cc表示下一步的下标
            int cr = r + dr[di];
            int cc = c + dc[di];
            // 判断是否需要调整方向
            if (0 <= cr && cr < row && 0 <= cc && cc < col && !seen[cr][cc]) {
                // 不需要
                r = cr;
                c = cc;
            } else {
                // 需要
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return res;
    }
}
