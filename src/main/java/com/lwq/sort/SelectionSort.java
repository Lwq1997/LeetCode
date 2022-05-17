package com.lwq.sort;

/**
 * 选择排序
 */
public class SelectionSort {
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
        for(int i= 0; i <arr.length; i++) {
            int min = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    private static void swap(int[] arr, int j, int min) {
        int temp = arr[j];
        arr[j] = arr[min];
        arr[min] = temp;
    }
}
