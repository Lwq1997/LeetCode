package com.lwq;

import java.util.HashSet;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LeetCode128 {
    public static void main(String[] args) {
        longestConsecutive(new int[]{100,4,200,1,3,2});
    }
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if(!set.contains(num-1)){
                // 如果是最长的子序列，那么他的值-1这个数字肯定不在set中
                int curr = 1;
                int currNum = num;
                while (set.contains(++currNum)){

                    curr++;
                }
                res = Math.max(res,curr);
            }
        }
        return res;
    }
}
