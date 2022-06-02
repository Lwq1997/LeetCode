package com.lwq.codecatalog.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode54 {
    public static void main(String[] args) {
        List<Integer> integers = new LeetCode54().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        System.out.println(integers);
    }

    /**
     * 模拟
     * <p>
     * 定义4个变量:l,r,t,b,分别表示左右上下的边界
     * <p>
     * 然后开始循环
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        while (true) {
            //从左到右
            for (int i = l; i <= r; i++) {
                res.add(matrix[t][i]);
            }
            t++; // 更新上边界
            if (t > b) {
                break;
            }
            //从上到下
            for (int i = t; i <= b; i++) {
                res.add(matrix[i][r]);
            }
            r--; // 更新右边界
            if (r < l) {
                break;
            }
            //从右到左
            for (int i = r; i >= l; i--) {
                res.add(matrix[b][i]);
            }
            b--; // 更新下边界
            if (t > b) {
                break;
            }
            //从下到上
            for (int i = b; i >= t; i--) {
                res.add(matrix[i][l]);
            }
            l++; // 更新左边界
            if (l > r) {
                break;
            }
        }
        return res;
    }
}
