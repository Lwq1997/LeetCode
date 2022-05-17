package com.lwq.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        // 新建数组
        int[] arr = {5, 4, 3, 2, 1};
        // 排序
//        sort1(arr);
//        sort2(arr);
        // 打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void sort1(int[] arr) {
        // 等价于打牌，手里的牌都是有序的，每次把最大的牌放到最后
        // 插入排序就是每次排序好部分数组
        // 第一次排序 0 - 0 （默认完成）
        // 第二次排序 0 - 1
        // ...
        for (int end = 1; end < arr.length; end++) {
            int curIndex = end;
            while (curIndex - 1 >= 0 && arr[curIndex - 1] > arr[curIndex]) {
                swap(arr, curIndex-1, curIndex);
                curIndex--;
            }
        }
    }

    private static void sort2(int[] arr) {
        // 等价于打牌，手里的牌都是有序的，每次把最大的牌放到最后
        // 插入排序就是每次排序好部分数组
        // 第一次排序 0 - 0 （默认完成）
        // 第二次排序 0 - 1
        // ...
        for (int end = 1; end < arr.length; end++) {
            for(int pre = end - 1; pre >= 0 && arr[pre] > arr[pre+1]; pre--){
                swap(arr, pre, pre+1);
            }
        }
    }


    private static void swap(int[] arr, int j, int min) {
        int temp = arr[j];
        arr[j] = arr[min];
        arr[min] = temp;
    }
}
