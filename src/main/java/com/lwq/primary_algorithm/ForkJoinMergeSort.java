package com.lwq.primary_algorithm;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinMergeSort {
    public static void main(String[] args) {
        int[] arr = {123, 345, 234, 453, 123, 5463, 2564, 78345, 67834, 56, 73, 45, 567, 35, 234, 2, 3, 235, 46, 5, 45, 242};
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MergeSort(arr));
        System.out.println(Arrays.toString(arr));
    }

    private static class MergeSort extends RecursiveAction {
        private int[] arr;

        public MergeSort(int[] arr) {
            this.arr = arr;
        }


        @Override
        protected void compute() {
            if (arr.length > 1) {
                int[] leftArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
                int[] rightArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

                // 这里分成两份执行
                invokeAll(new MergeSort(leftArr), new MergeSort(rightArr));

                // 合并且排序
                merge(leftArr, rightArr, arr);
            }
        }

        private void merge(int[] leftArray, int[] rightArray, int[] intArr) {
            // i：leftArray数组索引，j：rightArray数组索引，k：intArr数组索引
            int i = 0, j = 0, k = 0;
            while (i < leftArray.length && j < rightArray.length) {
                // 当两个数组中都有值的时候，比较当前元素进行选择
                if (leftArray[i] < rightArray[j]) {
                    intArr[k] = leftArray[i];
                    i++;
                } else {
                    intArr[k] = rightArray[j];
                    j++;
                }
                k++;
            }

            // 将还剩余元素没有遍历完的数组直接追加到intArr后面
            if (i == leftArray.length) {
                for (; j < rightArray.length; j++, k++) {
                    intArr[k] = rightArray[j];
                }
            } else {
                for (; i < leftArray.length; i++, k++) {
                    intArr[k] = leftArray[i];
                }
            }
        }
    }
}
