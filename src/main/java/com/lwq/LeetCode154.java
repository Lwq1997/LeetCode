package com.lwq;


/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 *
 * 输入: [2,2,2,0,1]
 * 输出: 0
 *
 */
public class LeetCode154 {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int res = findMin(nums);
        System.out.println(res);
    }

    /**
     * right--
     * 此操作不会使数组越界：因为迭代条件保证了 right > left >= 0；
     * 此操作不会使最小值丢失：假设 nums[right] 是最小值，有两种情况：
     * 若 nums[right] 是唯一最小值：那就不可能满足判断条件 nums[mid] == nums[right]，因为 mid < right（left != right 且 mid = (left + right) // 2 向下取整）；
     * 若 nums[right] 不是唯一最小值，由于 mid < right 而 nums[mid] == nums[right]，即还有最小值存在于 [left, right - 1] 区间，因此不会丢失最小值。
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
            }else {
                right--;  // 如果和右边想等了，最小值可能在mid的左边，也可能在mid的右边，这时候需要让right-1
            }
        }
        return nums[left];    /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
   }

}

