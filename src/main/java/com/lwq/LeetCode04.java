package com.lwq;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class LeetCode04 {
    public static void main(String[] args) {
        findMedianSortedArrays2(new int[]{1,2}, new int[]{-1,3});
    }

    /**
     * 归并后，返回中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int index = 0, i = 0, j = 0;
        while (index != (m + n)) {
            if (i == m) {
                while (j < n) {
                    nums[index++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i < m) {
                    nums[index++] = nums1[i++];
                }
                break;
            }

            nums[index++] = nums1[i] > nums2[j] ? nums2[j++] : nums1[i++];
        }
        if (index % 2 == 0) {
            // 如果长度是偶数 6
            // (nums[2]+nums[3])/2
            return (nums[index / 2] + nums[index / 2 - 1]) / 2.0;
        } else {
            // 如果长度是偶数 5
            // nums[2]
            return nums[index / 2];
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, m - 1, nums2, 0, n - 1, left) + getKth(nums1, 0, m - 1, nums2, 0, n - 1, right)) * 0.5;
    }

    private static double getKth(int[] nums1, int aStart, int aEnd, int[] nums2, int bStart, int bEnd, int k) {
        int len1 = aEnd - aStart + 1;
        int len2 = bEnd - bStart + 1;

        if (len1 > len2) {
            // 这一步就是为了保证nums1长度上小于nums2
            return getKth(nums2, bStart, bEnd, nums1, aStart, aEnd, k);
        }
        if (len1 == 0) {
            // 如果前一个数组空了，那就直接返回第二个数组中的特定下标的元素
            return nums2[bStart + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[aStart], nums2[bStart]);
        }
        // 这里的min是需要看看k/2是不是超了数组本身的长度，如果过了，就设置为len即可
        // 剪一是为了满足数组从0开始的下标
        int aIndex = aStart + Math.min(len1, k / 2) - 1;
        int bIndex = bStart + Math.min(len2, k / 2) - 1;
        if (nums1[aIndex] > nums2[bIndex]) {
            // 排除nums2中前面bindex+1元素，k的值也需要改变
            return getKth(nums1, aStart, aEnd, nums2, bIndex + 1, bEnd, k - (bIndex - bStart + 1));
        } else {
            return getKth(nums1, aIndex + 1, aEnd, nums2, bStart, bEnd, k - (aIndex - aStart + 1));
        }
    }

    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        // right 保存当前循环的结果
        // left 保存上一次的结果
        int left = -1;
        int right = -1;
        // aStart 表示当前A的下标
        // bStart 表示当前B的下标
        int aStart = 0;
        int bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                // 只要遍历的次数没有到len/2。并且当前下标A[aStart] < B[bStart]
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }

        if ((len & 1) == 0) {
            // 偶数，我们需要知道第 len/2和 len/2+1 个数
            return (left + right) / 2.0;
        } else {
            // 奇数，我们需要知道第 （len+1）/2 个数就可以了
            return right;
        }

    }
}
