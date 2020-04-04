package com.lwq;

import java.util.Arrays;

public class SingleThreadMergeSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 35, 56, 345, 123, 567, 134, 34, 678, 3123, 546, 678, 3, 6734, 567, 234, 567};
        mergesort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergesort(int[] arr) {
        if (arr.length > 1) {
            int[] leftArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
            int[] rightArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            mergesort(leftArr);
            mergesort(rightArr);

            merge(leftArr, rightArr, arr);
        }
    }

    private static void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        if (i == leftArr.length) {
            for (; j < rightArr.length; j++, k++) {
                arr[k] = rightArr[j];
            }
        }
        if (j == rightArr.length) {
            for (; i < leftArr.length; i++, k++) {
                arr[k] = leftArr[i];
            }
        }
    }
}
