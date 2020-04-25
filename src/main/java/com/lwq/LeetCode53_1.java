package com.lwq;

/**
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 */
public class LeetCode53_1 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //找到右边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        int rightbund = right;
        left = 0;
        right = nums.length - 1;
        // 找到左边界
        while (left <= right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
                // 因为最后的截止条件是left+1=right,如果此时的mid就是最左边边界，right不减一，最后left=mid+1，而不是等于mid。
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1; // 左闭右开和左闭右闭唯一的不同是在这里，还有下面的越界判断
            }
        }

        int leftbund = left;
        return rightbund - leftbund + 1;
    }
}
