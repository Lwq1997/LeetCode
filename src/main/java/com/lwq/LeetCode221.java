package com.lwq;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 */
public class LeetCode221 {
    public static void main(String[] args) {

    }
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length==0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int maxmatrix = 0;
        int[][] dp = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if (matrix[i][j] == '1') {
                    if(i==0 || j==0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                    maxmatrix = Math.max(maxmatrix,dp[i][j]);
                }
            }
        }
        return maxmatrix*maxmatrix;
    }
}
