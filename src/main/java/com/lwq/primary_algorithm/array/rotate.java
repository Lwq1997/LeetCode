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
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 */
public class rotate {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        rotate(arr,3);
        for(int i = 0;  i < arr.length ; i++){
            System.out.print(arr[i]+" ");
        }

    }

    /**
     * 每次移动一轮，移动k次
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        int newk = k%length;
        int temp ;
        for(int i = 0; i < newk;i++){
            temp = nums[length-1];
            for(int j = length-2;j>=0;j--){
                nums[j+1] = nums[j];
            }
            nums[0] = temp;
        }
    }

    /**
     * 开辟一个新的数组空间
     * @param nums
     * @param k
     */
    public static void rotate1(int[] nums, int k) {
        int length = nums.length;
        int[] newnums = new int[length];
        for(int i = 0;  i<length;i++){
            newnums[(i+k)%length]=nums[i];
        }
        System.arraycopy(newnums, 0, nums, 0, length);
    }

}
