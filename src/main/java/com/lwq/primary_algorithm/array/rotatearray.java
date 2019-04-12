package com.lwq.primary_algorithm.array;

/**
 * @Author: Lwq
 * @Date: 2018/8/25 15:45
 * @Version 1.0
 * @Describe 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
 */

/**
 *原地旋转图像
 *
 * 输入
 *  [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * 输出
 * [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 */
public class rotatearray {

    /**
     * 原地旋转
     *
     * 每个数的旋转会关联到四个数
     * a[i][j]
     * a[n-1-j][i]
     * a[n-1-i][n-1-j]
     * a[n-1-(n-1-j)][n-1-i]=a[j][n-1-i]
     *
     *
     * int temp = matrix[i][j];
     * matrix[i][j] = matrix[n-1-j][i];
     * matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
     * matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
     * matrix[j][n-1-i] = temp;
     *
     * 这样旋转一次就相当于把四个数都旋转了，我们在二维数组中只需要转几个特定的数就可以了
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n;j++){
                if(i<n/2&&i<=j&&j<n-1-i){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n-1-j][i];
                    matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                    matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                    matrix[j][n-1-i] = temp;
                }
            }
        }
    }
}
