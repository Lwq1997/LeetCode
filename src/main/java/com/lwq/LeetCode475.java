package com.lwq;

import java.util.Arrays;

/**
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 *
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 *
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 *
 * 说明:
 *
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 示例 1:
 *
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 *
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 *
 */
public class LeetCode475 {
    public static void main(String[] args) {
        int[] houses = {1,2,3,4};
        int[] heaters = {2,4};
        System.out.println(findRadius(houses,heaters));
    }

    /**
     * 二分查找，找到每个房子距离最近热水器的距离，然后求一个最大值
     * @param houses
     * @param heaters
     * @return
     */
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int res = 0;

        for (int house : houses) {
            // 二分搜索当前 house 在 heaters 中的位置
            int index = binarySearch(heaters, house);

            if (index < 0) { // 说明没找到，index 等于当前的 house 应该在 heaters 的位置的负数
                // 计算出当前的 house 应该在 heaters 数组中的位置
                index = -(index + 1);
                // 计算当前 house 离左边最近的 heater 的距离
                int leftDist = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                // 计算当前 house 离右边最近的 heater 的距离
                int rightDist = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

                // 当前 house 需要的 heater 的半径取 leftDist 和 rightDist 的最小值
                res = Math.max(res, Math.min(leftDist, rightDist));
            }
        }

        return res;
    }

    public static int binarySearch(int[] heaters, int key) {
        int left = 0;
        int right = heaters.length-1;
        while (left<=right){
            int mid = (left+right) >>> 1;
            if(heaters[mid] > key){
                right = mid - 1;
            }else if(heaters[mid] < key){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -(left + 1);
    }
}
