package com.lwq;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class LeetCode354 {
    /**
     * 先对宽度 w 进行升序排序，如果遇到 w 相同的情况，则按照高度 h 降序排序。
     * 之后把所有的 h 作为一个数组，在这个数组上计算 LIS 的长度就是答案。
     */
    public int maxEnvelopes(int[][] envelopes) {

        int len = envelopes.length;
        if(len == 0 || len == 1){
            return len;
        }
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0]? b[1] - a[1] : a[0]-b[0];
            }
        });
        // 对高度数组寻找 LIS
        int[] height = new int[len];
        for (int i = 0; i < len; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
    }

    // 参考第300题
    private int lengthOfLIS(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        // 默认在这之前没有比我更小的值了
        Arrays.fill(dp,1);
        int res = 1;
        for(int i = 0; i < height.length; i++){
            for(int j = 0; j < i ; j++){
                if(height[j] < height[i]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                    res = Math.max(res,dp[i]);
                }
            }
        }
        return res;
    }
}
