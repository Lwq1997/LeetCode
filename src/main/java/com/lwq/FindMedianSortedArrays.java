package com.lwq;

/**
 * @author Lwq
 * @create 2018-06-13 13:08
 * @desc
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 *
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 中位数是 2.0
 **/
public class FindMedianSortedArrays {
    /**
     * 先把两个排序数组按顺序放到一个大的数组中，
     * 然后取大数组的中位数
     * <p>
     * 复杂度：O（m+n）
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays01(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] arr = new int[len1 + len2];
        int end1 = len1 - 1;
        int end2 = len2 - 1;
        int index = arr.length - 1;
        while (end1 >= 0 && end2 >= 0) {
            if (nums1[end1] > nums2[end2]) {
                arr[index] = nums1[end1];
                index--;
                end1--;
            } else {
                arr[index] = nums2[end2];
                index--;
                end2--;
            }
        }
        while (end1 >= 0) {
            arr[index] = nums1[end1];
            end1--;
            index--;
        }
        while (end2 >= 0) {
            arr[index] = nums2[end2];
            end2--;
            index--;
        }
        if (arr.length % 2 == 0) {
            return (arr[arr.length / 2] + arr[(arr.length - 1) / 2]) / 2.0;
        } else {
            return arr[arr.length / 2];
        }
    }

    /**
     * 假设数组分别记为 A，B，当前需要搜索第 k 大的数，
     * 于是我们可以考虑从数组 A 中取出前 m 个元素，
     * 从数组 B 中取出前 k - m 个元素。由于数组 A，B 分别排序，
     * 则 A[m - 1] 大于从数组 A 中取出的其他所有元素，
     * B[k - m - 1] 大于数组 B 中取出的其他所有元素。
     * 此时，尽管取出元素之间的相对大小关系不确定，
     * 但 A[m - 1] 与 B[k - m - 1] 的较大者一定是这 k 个元素中最大的。
     *
     * <p>
     * 为叙述方便，假设 A[m - 1] 是较小的那个元素，
     * 那么我们可以把 A[0]，A[1]...A[m - 1] 排除掉，
     * 并且更新 k 值为 k - m，
     * 也就是下一次就是从剩余的元素中寻找第 k - m 大的元素，
     * 这样，我们就完成了一次范围缩小，同理进行下一轮的操作。
     * 那么什么时候停止操作呢？分两种情况：
     *
     * 1. 当某个数组的数都被取完了，那么直接返回另一个数组的后 k 个元素即可。
     *
     * 2.当 k = 1 时，也就是只需再找一个数即可，也就是取两者当前较小的那个即可。
     */
    public double findMedianSortedArrays02(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            return (helper(nums1, 0, nums2, 0, len / 2) + helper(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        }
        return helper(nums1, 0, nums2, 0, (len + 1) / 2);
    }

    public double helper(int[] nums1, int m, int[] nums2, int n, int k) {
        if(m >= nums1.length){
            return nums2[n+k-1];
        }
        if(n >= nums2.length){
            return nums1[m+k-1];
        }
        if(k==1){
            return Math.min(nums1[m],nums2[n]);
        }
        int p1 = m + k / 2 - 1;
        int p2 = n + k / 2 - 1;
        int mid1 = p1 < nums1.length ? nums1[p1] : Integer.MAX_VALUE;
        int mid2 = p2 < nums2.length ? nums2[p2] : Integer.MAX_VALUE;
        if (mid1 < mid2) {
            return helper(nums1, m + k / 2, nums2, n, k - k / 2);
        }
        return helper(nums1, m, nums2, n + k / 2, k - k / 2);
    }

    public double findMedianSortedArrays03(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        //填充result数组，从小到大排序（这个数组的大小只有总数的一半）
        for (int k = 0; k < (m + n)/2+1; k++) {
            if (i >= m) {
                result[k] = nums2[j++];
            } else if (j >= n) {
                result[k] = nums1[i++];
            } else if (nums2[j] < nums1[i]) {
                result[k] = nums2[j++];
            } else {
                result[k] = nums1[i++];
            }
        }
        if (m+n<2) {
            return result[0];
        }else if ((m+n)%2==0){
            return (result[(m+n)/2]+result[(m+n)/2-1])/2.0;
        }else{
            return result[(m+n)/2];
        }
    }
}
