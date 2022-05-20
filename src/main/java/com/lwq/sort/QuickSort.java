package com.lwq.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {1, 5, 2, 6, 2, 6, 7, 2};
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    private static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            swap(nums, left + (int) (Math.random() * (right - left + 1)), right);
            int[] p = partition(nums, left, right);
            quickSort(nums, left, p[0] - 1);
            quickSort(nums, p[1] + 1, right);
        }
    }

    private static int[] partition(int[] nums, int left, int right) {
        int less = left - 1;
        int more = right;
        int index = left;
        while (index < more) {
            //每一次循环就是看left这个数的值和more这个数的值的大小关系
            if (nums[index] < nums[right]) {
                //此时把小于标记位的区间右移，left往后移动一位
                swap(nums, ++less, index++);
            } else if (nums[index] > nums[right]) {
                //此时把标记位的前一个数字和left交换
                swap(nums, --more, index);
            } else {
                index++;
            }
        }
        //此时right还是最右边，需要和more的位置交换
        swap(nums, more, right);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
