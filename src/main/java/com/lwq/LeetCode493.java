package com.lwq;

/**
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 * <p>
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode493 {
    public int reversePairs(int[] nums) {
        // 使用归并排序，从小到大的进行排序
        if(nums.length < 2) {
            return 0;
        }
        return sort(nums,0,nums.length - 1);

    }

    public int sort(int[] nums,int left,int right) {
        if(left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        // 计算左边的翻转对
        int leftRes = sort(nums,left,mid);
        // 计算左边的翻转对
        int rightRes = sort(nums,mid + 1,right);

        return leftRes + rightRes + merge(nums,left,mid,right);
    }

    public int merge(int[] nums,int left,int mid,int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int res = 0;
        int flag = 0;
        // 计算翻转对
        while(i <= mid && j <= right) {
            if(nums[i] > 2 * (long)nums[j]) {
                res += mid - i + 1;
                j++;
            }else {
                i++;
            }
        }

        i = left;
        j = mid + 1;

        // 进行排序
        while(i <= mid && j <= right) {

            if(nums[i] < nums[j]) {
                temp[flag++] = nums[i++];
            }else {
                temp[flag++] = nums[j++];
            }
        }

        while(i <= mid) {
            temp[flag++] = nums[i++];
        }
        while(j <= right) {
            temp[flag++] = nums[j++];
        }
        for(int k = 0;k < temp.length;k++) {
            nums[left + k] = temp[k];
        }
        return res;
    }
}
