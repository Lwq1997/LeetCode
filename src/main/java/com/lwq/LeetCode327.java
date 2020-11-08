package com.lwq;

import com.sun.tools.javah.LLNI;

import java.util.Arrays;

/**
 * 327. 区间和的个数
 *
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 *
 * 示例:
 *
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 *
 * 数组 A 有多少个连续的子数组，其元素只和在 [lower,upper] 的范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-range-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode327 {
    public static void main(String[] args) {
        countRangeSum(new int[]{-2,5,-1},-2,2);
    }
    /**
     * 找一个前缀和数组P，则i到j的求和等价于P[j]−P[i−1]：SUM(i,j)=P[j]−P[i−1]
     * 我们枚举所有的二元组 (i,j)，算出 S(i,j) 并判断其是否在范围内即可。时间复杂度为 O(n^2)
     *
     * 由于
     * lower≤P[j]−P[i−1]≤upper
     * 我们可以得到 P[i-1]应该满足的不等式
     * P[j]−upper≤P[i−1]≤P[j]−lower
     *
     *
     * lower≤n2[j] - n1[i] ≤upper
     * 我们在 n2中维护两个指针 l,r。起初，它们都指向 n2的起始位置。
     * 随后，我们考察 n1的第一个元素。首先，不断地将指针 l 向右移动，直到 n2≥n1[0]+lower 为止，此时， l 及其右边的元素均大于或等于 n1[0]+lower
     * 随后，再不断地将指针 r 向右移动，直到 n2[r] > n1[0] + upper为止，则 r 左边的元素均小于或等于 n1[0] + upper
     * 故区间 [l,r) 中的所有下标 j，都满足n2[j] - n1[0] 在 【lower,upper]范围内
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length+1];
        for(int i = 0; i < nums.length ; i++){
           s += nums[i];
           sum[i+1] = s;
        }
        Arrays.stream(sum).forEach(x -> System.out.println(x));
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    private static int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if(left == right){
            return 0;
        }else {
            int mid = (left+right)/2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            //l,r都指向n2的最左边
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组（排序前缀树)
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }
}
