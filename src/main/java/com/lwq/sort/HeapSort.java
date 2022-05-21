package com.lwq.sort;


/**
 * 归并排序
 */
public class HeapSort {
    public static void main(String[] args) {
        sort(new int[]{1, 4, 6, 2, 6, 2, 6, 7, 1, 4, 7, 8, 3, 7, 8, 3, 100});
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // 初始化大根堆
        int heapSize = arr.length;
        for (int i = 0; i < heapSize; i++) {//O(N)
            heapInsert(arr, i);//O(logn)
        }
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {//O(N)
            heapify(arr, 0, heapSize);//O(logn)
            swap(arr, 0, --heapSize);//O(1)
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 堆排序,形成一个大根堆,然后将堆顶元素与最后一个元素交换,再将堆顶元素与堆顶元素交换,
     * 这个方法的时间复杂度：O(logn)
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 调整一个数组为大根堆
     * 这个方法的时间复杂度：O(logn)
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //说明index有左孩子
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//有孩子，则拿到左右孩子中较大的，否则直接拿左孩子
            largest = arr[index] > arr[largest] ? index : largest;// 此时larget为一颗子树，三个节点中的最大值下标
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
