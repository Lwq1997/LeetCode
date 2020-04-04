package com.lwq;

import java.util.*;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 在其中任意一行放置一个皇后，则与此皇后同行，同列，同对角线的都不允许再放其他皇后
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class LeetCode51_3 {
    static int count = 0;

    public static void main(String[] args) {
        solveNQueens(1);
        System.out.println(count);
    }

    public static void solveNQueens(int n) {
        backtrack(n,0,0,0,0);
    }

    public static void backtrack(int n,int row,int colomn,int pie,int na) {
        if (row == n) {
            count++;
            return;
        }
        // 取出当前行可放置皇后的格子
        int bits = (~(colomn|pie|na)) & ((1 << n)-1);
        //如果bits = 00000 说明已经不能放了
        while(bits > 0) {
            // 每次从当前行可用的格子中取出最右边位为 1 的格子放置皇后
            int p = bits & -bits;

            // 紧接着在下一行继续放皇后
            backtrack(n,row+1, colomn | p, (pie|p) << 1, (na | p) >> 1);

            // 当前行最右边格子已经选完了，将其置成 0，代表这个格子已遍历过
            bits = bits & (bits-1);
        }
    }
}
