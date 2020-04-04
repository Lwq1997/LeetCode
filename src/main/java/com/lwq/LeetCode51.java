package com.lwq;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
public class LeetCode51 {
    List<List<String>> res;


    public List<List<String>> solveNQueens(int n) {
        if (n <= 0){
            return null;
        }
        res = new LinkedList<>();
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars,'.');
        }
        backtrack(board,0);
        return res;
    }

    /**
     * board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     *
     * def backtrack(路径, 选择列表):
     *     if 满足结束条件:
     *         result.add(路径)
     *         return
     *
     *     for 选择 in 选择列表:
     *         做选择
     *         backtrack(路径, 选择列表)
     *         撤销选择
     *
     * @param board 是该问题的其中一个解
     * @param row
     */
    private void backtrack(char[][] board, int row) {
        if(row == board.length){
            res.add(charToString(board));
            return;
        }
        int n = board[row].length;
        for(int col = 0; col < n ; col++){
            if(!isValid(board,row,col)){
                continue;
            }
            board[row][col] = 'Q';
            backtrack(board,row+1);
            board[row][col] = '.';
        }

    }

    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        // check is valid in col  检查同列中是否有皇后
        for (char[] chars : board) if (chars[col] == 'Q') return false;
        // check is valide upright 检查右斜线上有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // check is valide upleft 检查左斜线是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> charToString(char[][] board) {
        LinkedList<String> result = new LinkedList<>();
        for (char[] chars : board) {
            result.add(String.valueOf(chars));
        }
        return result;
    }
}
