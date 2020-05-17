package com.lwq;

/**
 *
 * 小于 K 的两数之和
 */
public class LeetCode1099 {

    public int twoSumLessThanK(int[] A, int K) {
        int left = 0, right = A.length - 1;
        int res = Integer.MIN_VALUE;
        while (left < right) {
            if (A[left] + A[right] >= K) {
                right--;
            } else {
                res = Math.max(res, A[left] + A[right]);
                left++;
            }
        }
        return res;
    }
}
