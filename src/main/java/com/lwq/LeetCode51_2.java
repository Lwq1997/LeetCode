package com.lwq;

import java.util.*;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 在其中任意一行放置一个皇后，则与此皇后同行，同列，同对角线的都不允许再放其他皇后
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class LeetCode51_2 {
    static LinkedList<List<String>> res = new LinkedList<>();
    static Set<Integer> colSet = new HashSet<>();
    static Set<Integer> masterSet = new HashSet<>();
    static Set<Integer> slaveSet = new HashSet<>();


    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(10);
        System.out.println(lists.size());
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s + " ");
            }
            System.out.println();
        }

    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] chars : board) Arrays.fill(chars, '.');
        backtrack(board, 0);
        return res;
    }

    private static void backtrack(char[][] board, int row) {
        if (row == board.length) {
            res.add(charToString(board));
            return;
        }
        for (int col = 0; col < board[row].length; col++) {
            if (!isValide(board, row, col)) continue;
            updateRecords(board, row, col);
            backtrack(board, row + 1);
            updateRecords(board, row, col);
        }
    }

    private static void updateRecords(char[][] board, int row, int col) {
        if (colSet.contains(col)) {
            board[row][col] = '.';
            colSet.remove(col);
            masterSet.remove(row - col);
            slaveSet.remove(row + col);
        } else {
            board[row][col] = 'Q';
            colSet.add(col);
            masterSet.add(row - col);
            slaveSet.add(row + col);
        }
    }

    private static boolean isValide(char[][] board, int row, int col) {
        return !colSet.contains(col)
                && !masterSet.contains(row - col)
                && !slaveSet.contains(row + col);
    }

    private static List<String> charToString(char[][] board) {
        LinkedList<String> result = new LinkedList<>();
        for (char[] chars : board) {
            result.add(String.valueOf(chars));
        }
        return result;
    }
}
