package com.lwq.basic.array;

import java.util.HashSet;

/**
 * @Author: Lwq
 * @Date: 2018/8/25 15:15
 * @Version 1.0
 * @Describe  判断一个 9x9 的数独是否有效
 */
public class isValidSudoku {

    public static void main(String[] args) {

    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[9];
            int[] col = new int[9];
            int[] cube = new int[9];
            for (int j = 0; j < 9; j++) {
                int cubeX = 3 * (i / 3) + j / 3;
                int cubeY = 3 * (i % 3) + j % 3;
                if (board[i][j] != '.') {
                    if (row[board[i][j] - '1'] == 1) {
                        return false;
                    } else {
                        row[board[i][j] - '1'] = 1;
                    }
                }
                if (board[j][i] != '.') {
                    if (col[board[j][i] - '1'] == 1) {
                        return false;
                    } else {
                        col[board[j][i] - '1'] = 1;
                    }
                }
                if (board[cubeX][cubeY] != '.') {
                    if (cube[board[cubeX][cubeY] - '1'] == 1) {
                        return false;
                    } else {
                        cube[board[cubeX][cubeY] - '1'] = 1;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku1(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                // 检查第i行，在横坐标位置
                if (board[i][j] != '.' && !row.add(board[i][j]))
                    return false;
                // 检查第i列，在纵坐标位置
                if (board[j][i] != '.' && !column.add(board[j][i]))
                    return false;
                // 行号+偏移量
                int RowIndex = 3 * (i / 3) + j / 3;
                // 列号+偏移量
                int ColIndex = 3 * (i % 3) + j % 3;
                //每个小九宫格，总共9个
                if (board[RowIndex][ColIndex] != '.'
                        && !cube.add(board[RowIndex][ColIndex]))
                    return false;
            }
        }
        return true;
    }
}