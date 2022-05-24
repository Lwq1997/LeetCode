package test;

import com.lwq.QuickSort;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    //测试快速排序
    @org.junit.jupiter.api.Test
    void testQuickSort() {
        int[] arr = {1, 5, 3, 2, 4};
        QuickSortTest.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    public static void quickSort(int[] arr){
        quickSortWith(arr,0,arr.length-1);
    }

    private static void quickSortWith(int[] arr, int left, int right) {
        if (left < right) {
            int[] p = partition(arr, left, right);
            quickSortWith(arr, left, p[0] - 1);
            quickSortWith(arr, p[1] + 1, right);
        }
    }

    private static int[] partition(int[] arr, int left, int right) {
        int less = left-1;
        int more=  right;
        int index = left;
        while (index < more){
            if(arr[right]<arr[index]){
                swap(arr,index,--more);
            }else if(arr[right]>arr[index]){
                swap(arr,++less,index++);
            }else{
                index++;
            }
        }
        //more之后的元素（包括more）都比right大，交换后，那个参考值现在就是more的位置
        swap(arr, more, right);
        return new int[]{less+1,more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}