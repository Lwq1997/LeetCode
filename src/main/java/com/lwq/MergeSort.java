package com.lwq;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,5,6,1,51,3,4,5,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        mergeSort(arr,0,arr.length-1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if(left == right){
            return;
        }
        int mid = left + ((right-left)>>1);
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int index = 0;
        int p1 = left;
        int p2 = mid+1;
        while (p1<=mid && p2 <=right){
            help[index++] = arr[p1]<arr[p2] ? arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[index++] = arr[p1++];
        }
        while (p2<=right){
            help[index++] = arr[p2++];
        }
        for(int i = 0; i < index ; i++){
            arr[left+i] = help[i];
        }
    }
}
