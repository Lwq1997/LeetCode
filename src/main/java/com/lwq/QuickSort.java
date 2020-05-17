package com.lwq;

import java.util.Arrays;

/**
 * 快排
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,4,5,2,4,1,5,1,6,7,8,4};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0] - 1);
            quickSort(arr, p[1] + 1, right);
        }
    }

    public static int[] partition(int[] arr, int left, int right) {
        int less = left-1;
        int more = right;
        int index = left;
        while(index<more){
            if(arr[index]<arr[right]){
                //其实就是less和index后移一位，没有交换
                swap(arr, ++less, index++);
            }else if(arr[index]>arr[right]){
                // 第一次的时候保护一下right位置的数字，不让他变动，所以--more
                swap(arr, --more, index);
            }else{
                index++;
            }
        }
        //more之后的元素（包括more）都比right大，交换后，那个参考值现在就是more的位置
        swap(arr, more, right);
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
