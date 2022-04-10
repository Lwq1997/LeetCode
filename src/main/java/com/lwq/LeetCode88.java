package com.lwq;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class LeetCode88 {
    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] num2 = new int[]{2, 5, 6};
//        merge1(num1, 3, num2, 3);
        merge2(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int cur;
        int[] sorted = new int[m + n];
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        while (m > 0 && n > 0) {
            nums1[--len] = nums2[n - 1] >= nums1[m - 1] ? nums2[--n] : nums1[--m];
        }
        // 如果num1的元素还没有完成，则不需要再处理了，因为num2的都比num1的大，放着不动即可
        // 如果num2还有元素没有填充进去，则需要填充到num1的前面
        System.arraycopy(nums2, 0, nums1, 0, n);
    }
}
