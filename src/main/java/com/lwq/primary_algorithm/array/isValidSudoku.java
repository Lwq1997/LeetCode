package com.lwq.primary_algorithm.array;

/**
 * @Author: Lwq
 * @Date: 2018/8/25 15:15
 * @Version 1.0
 * @Describe  判断一个 9x9 的数独是否有效
 */
public class isValidSudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] block = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9;j++){
                if (board[i][j]!='.'){
                    int num = Integer.valueOf(board[i][j])-48;
                    if(col[j][num] || row[i][num] || block[i / 3 * 3 + j / 3][num]){
                        return false;
                    }else {
                        col[j][num] = true;
                        row[i][num] = true;
                        block[i / 3 * 3 + j / 3][num] = true;
                    }
                }
            }
        }
        return true;
    }
}