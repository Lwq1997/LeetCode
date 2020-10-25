package com.lwq;

/**
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * <p>
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * <p>
 * 如果不含有 “山脉” 则返回 0。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * <p>
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 */
public class LeetCode845 {
    /**
     * 先找到比左右两侧大的数 然后以这个数为中心 依次找到左右的长度
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int res = 0;
        if (A == null || A.length < 2) {
            return res;
        }
        for (int i = 1; i < A.length - 1; i++) {
            //找到中间元素大于两边元素的w位置
            if (A[i - 1] < A[i] && A[i] < A[i + 1]) {
                int left = i - 1;
                int right = i + 1;
                while (left > 0 && A[left - 1] < A[left]) {
                    left--;
                }
                while (right < A.length - 1 && A[right] < A[right + 1]) {
                    right++;
                }
                res = Math.max(res, (right - left + 1));
            }
        }
        return res;
    }
}
