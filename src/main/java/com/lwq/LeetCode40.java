package com.lwq;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class LeetCode40 {
    public static void main(String[] args) {
        getLeastNumbers1(new int[]{1,8,9,2},2);
    }

    // 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
    // 1. 若目前堆的大小小于K，将当前数字放入堆中。
    // 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
    //    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);

        for (int num : arr) {
            if(queue.size() < k){
                queue.offer(num);
            }else if(num < queue.peek()){
                queue.poll();
                queue.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[queue.size()];
        int idx = 0;
        for(int num: queue) {
            res[idx++] = num;
        }
        return res;

    }

    public static int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        } else if (arr.length <= k) {
            return arr;
        }

        // 原地不断划分数组
        partitionArray(arr, 0, arr.length - 1, k);

        // 数组的前 k 个数此时就是最小的 k 个数，将其存入结果
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    static void  partitionArray(int[] arr, int lo, int hi, int k) {
        // 做一次 partition 操作
        int m = partition(arr, lo, hi);
        // 此时数组前 m 个数，就是最小的 m 个数
        if (k == m) {
            // 正好找到最小的 k(m) 个数
            return;
        } else if (k < m) {
            // 最小的 k 个数一定在前 m 个数中，递归划分
            partitionArray(arr, lo, m-1, k);
        } else {
            // 在右侧数组中寻找最小的 k-m 个数
            partitionArray(arr, m+1, hi, k);
        }
    }

    // partition 函数和快速排序中相同
    static int partition(int[] arr, int left, int right) {

        int less = left-1;
        int more = right;
        int index = left;
        while(index<more){
            if(arr[index]<arr[right]){
                //其实就是less和index后移一位，没有交换
                swap(arr, ++less, index++);
            }else if(arr[index]>arr[right]){
                swap(arr, --more, index);
            }else{
                index++;
            }
        }
        //more之后的元素（包括more）都比right大，交换后，那个参考值现在就是more的位置
        swap(arr, more, right);
        return more;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
