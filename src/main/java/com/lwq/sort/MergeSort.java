package com.lwq.sort;


import javafx.scene.control.Alert;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        sort(new int[]{1, 4, 6, 2, 6, 2, 6, 7, 1, 4, 7, 8, 3, 7, 8, 3});
        //断言
        System.out.println("+====");
        System.out.println(smallSum(new int[]{1, 4, 6, 2, 6}));
        assert smallSum(new int[]{1, 4, 6, 2, 6}) == 14;
    }

    private static int smallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process_small_sum(arr, 0, arr.length - 1);
    }

    private static int process_small_sum(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        return process_small_sum(arr, left, mid) + process_small_sum(arr, mid + 1, right) + merges_small_sum(arr, left, mid, right);
    }

    private static int merges_small_sum(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int res = 0;
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            res += arr[p1] < arr[p2] ? arr[p1] * (right - p2 + 1) : 0;
            //这里需要注意，如果左右两个数字相等，一定要先移动右边的下标
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
        return res;
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
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
