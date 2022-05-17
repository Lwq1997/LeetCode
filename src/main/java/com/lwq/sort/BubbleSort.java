package com.lwq.sort;

/**
 * 选择排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        // 新建数组
        int[] arr = {5, 4, 3, 2, 1};
        // 排序
        sort(arr);
        // 打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void sort(int[] arr) {
        // 冒泡排序就是一次一次把最大的数挪到最后
        // 第一次挪动 0 - n-1
        // 第二次挪动 0 - n-2
        // ...
        for(int i = arr.length - 1; i > 0 ; i--) {
            // 循环中相邻的数字进行比较
            // 第一次比较 0 - 1
            // 第二次比较 1 - 2
            // 第三次比较 2 - 3
            // 最后一次是 end - 1 和 end
            for(int end = 1; end <= i; end++) {
                // 如果前一个数大于后一个数，则交换
                if(arr[end - 1] > arr[end]) {
                    swap(arr, end - 1, end);
                }
            }
        }
    }


    private static void swap(int[] arr, int j, int min) {
        int temp = arr[j];
        arr[j] = arr[min];
        arr[min] = temp;
    }
}
