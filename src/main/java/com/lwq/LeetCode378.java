package com.lwq;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 *  
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *  
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 */
public class LeetCode378 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > mid) {
                i--;
            } else {
                // 那一列的数据都比mid小
                num += i + 1;
                j++;
            }
        }
        // 如果大于k，说明当前mid比第k小的数（x）小。此时应该去左边再找
        return num >= k;
    }

    public int kthSmallest1(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < matrix.length; i++) {
            // 把每一行的第一个元素放到对列中
            queue.add(new int[]{matrix[i][0], i, 0});
        }
        for (int index = 0; index < k - 1; index++) {
            // 这时候开始往后遍历k-1次就可以找到第k小的元素
            int[] num = queue.poll();
            if (num[2] < matrix.length - 1) {
                queue.add(new int[]{matrix[num[1]][num[2] + 1], num[1], num[2] + 1});
            }
        }
        return queue.poll()[0];
    }
}
