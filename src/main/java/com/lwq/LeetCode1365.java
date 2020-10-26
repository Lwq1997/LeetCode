package com.lwq;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class LeetCode1365 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] > nums[j]) {
                    tmp++;
                }
            }
            res[i] = tmp;
        }
        return res;
    }

    public int[] smallerNumbersThanCurrent1(int[] nums) {
        // nums: 3,4,1,7
        int n = nums.length;
        int[] res = new int[n];
        int[][] data = new int[n][2];
        // 构建二位数组，0下标记录数字，1下标记录位置

        for (int i = 0; i < nums.length; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        // 按二维数组的0下标排序
        // data:1,3,4,7
        //      2,0,1,3
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int prev = -1;
        // res:1,2,0,3
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            res[data[i][1]] = prev;
        }
        return res;
    }

    public static void main(String[] args) {
        smallerNumbersThanCurrent2(new int[]{3,4,1,7});
    }
    // 桶思想
    // 先用buck统计nums数组中每个数字的个数，然后遍历buck，通过累加计算每个数字前面有几个比它小的数，
    // 最后直接以nums为索引从buck中取答案就好了
    public static int[] smallerNumbersThanCurrent2(int[] nums) {
        // nums: 3,4,1,7
        int[] buck = new int[101];
        for (int num : nums) {
            // 0,1,0,1,1,0,0,1,0,0,0,0......
            buck[num] += 1;
        }
        for (int i = 1; i < buck.length; i++) {
            // 0,1,1,2,3,3,3,4,4,4,4,4......
            buck[i] += buck[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] != 0 ? buck[nums[i] - 1] : 0;
        }
        return nums;
    }

}
