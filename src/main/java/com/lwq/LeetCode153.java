package com.lwq;


/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 */
public class LeetCode153 {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int res = findMin(nums);
        System.out.println(res);
    }

    /**
     * 用二分法查找，需要始终将目标值（这里是最小值）套住，并不断收缩左边界或右边界。
     *
     * 1左值 < 中值, 中值 < 右值 ：没有旋转，最小值在最左边，可以收缩右边界
     * 2左值 > 中值, 中值 < 右值 ：有旋转，最小值在左半边，可以收缩右边界
     * 3左值 < 中值, 中值 > 右值 ：有旋转，最小值在右半边，可以收缩左边界
     * 4左值 > 中值, 中值 > 右值 ：单调递减，不可能出现
     *
     * 情况1、2是一类，情况3是另一类。
     *    如果中值 < 右值，则最小值在左半边，可以收缩右边界。
     *    如果中值 > 右值，则最小值在右半边，可以收缩左边界。
     *
     * @param nums
     * @return
     */
   public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;                /* 左闭右闭区间，如果用右开区间则不方便判断右值 */
        while (left < right) {                      /* 循环不变式，如果left == right，则循环结束 */
            int mid = left + (right - left) / 2;    /* 地板除，mid更靠近left */
            if (nums[mid] > nums[right]) {          /* 中值 > 右值，最小值在右半边，收缩左边界 */
                left = mid + 1;                     /* 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid */
            } else if (nums[mid] < nums[right]) {   /* 明确中值 < 右值，最小值在左半边，收缩右边界 */
                right = mid;                        /* 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处 */
            }
        }
        return nums[left];    /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
   }

    /**
     * 找到偏右的最大值，进而向右再移动一位，自然也就是最小值。
     * @param nums
     * @return
     */
    public static int findMin1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;  //不能加一，现在找的是最大值，mid可能就是最大值
            } else if (nums[mid] < nums[left]) {
                right = mid-1;  //需要减1，mid这个指是小于right，他肯定不是最大值
            }
        }
        return nums[(right+1)%nums.length];  //如果mid最后等于最右边的值了，再加一会越界
    }
}

