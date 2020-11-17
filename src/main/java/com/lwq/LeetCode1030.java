package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * <p>
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * <p>
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1030 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] re = new int[R * C][2];
        // 初始化桶大小
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        ArrayList<LinkedList<Pos>> bucket = new ArrayList<>(maxDist + 1);
        // 初始化一个桶
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new LinkedList<>());
        }

        // 往桶中添加值
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int d = dist(i, j, r0, c0);
                LinkedList<Pos> list = bucket.get(d);
                list.add(new Pos(i, j));
            }
        }

        // 遍历桶中的数据，然后写入二维数组中
        int cnt = 0;
        for (int i = 0; i <= maxDist; i++) {
            LinkedList<Pos> list = bucket.get(i);
            if (list.isEmpty()) {
                continue;
            }
            for (Pos p : list) {
                re[cnt][0] = p.r;
                re[cnt][1] = p.c;
                cnt++;
            }
        }

        return re;
    }

    private int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }


    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
