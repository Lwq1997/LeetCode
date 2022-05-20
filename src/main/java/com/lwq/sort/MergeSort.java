package com.lwq.sort;


/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        sort(new int[]{1,4,6,2,6,2,6,7,1,4,7,8,3,7,8,3});
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
    }
}
