package com.lwq.codecatalog.array;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode34 {
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] arr, int target) {
        int indexleft = mostLeftLessNumIndex(arr, target);
        int indexright = mostrightLessNumIndex(arr, target);

        return new int[]{indexleft, indexright};
    }

    private int mostrightLessNumIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                index = mid;
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    private int mostLeftLessNumIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                index = mid;
                right = mid - 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }
}
