package com.lwq.primary_algorithm.array;

/**
 * @author Lwq
 * @create 2018-08-21 15:53
 * @desc 旋转数组
 **/

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
*/
public class rotate {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        rotate_2(arr,3);
        for(int i = 0;  i < arr.length ; i++){
            System.out.print(arr[i]+" ");
        }

    }

    /**
     * 每次移动一轮，移动k次
     * @param nums
     * @param k
     */
    /*
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     */
    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        int newk = k%length;
        for(int i = 0; i < newk ;i++){
            int temp = nums[length-1];
            for(int j = nums.length-1; j > 0; j--){
                nums[j] = nums[j-1];
            }
            nums[0] = temp;
        }
    }

    /**
     * 先翻转0-n  7,6,5,4,3,2,1
     * 再翻转0-k  5,6,7,4,3,2,1
     * 再翻转k-n  5,6,7,1,2,3,4
     * @param nums
     * @param k
     */
    public static void rotate_2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }


    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
