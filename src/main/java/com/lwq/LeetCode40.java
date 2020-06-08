package com.lwq;

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
            if (queue.size() < k) {
                queue.offer(num);
            } else if (num < queue.peek()) {
                queue.poll();
                queue.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[queue.size()];
        int idx = 0;
        for (int num : queue) {
            res[idx++] = num;
        }
        return res;

    }

    public int[] getLeastNumbers1(int[] arr, int k) {
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

    public void partitionArray(int[] arr, int lo, int hi, int k) {
        // 做一次 partition 操作
        int m = partition(arr, lo, hi);
        // 此时数组前 m 个数，就是最小的 m 个数
        if (k == m) {
            // 正好找到最小的 k(m) 个数
            return;
        } else if (k < m) {
            // 最小的 k 个数一定在前 m 个数中，递归划分
            partitionArray(arr, lo, m - 1, k);
        } else {
            // 在右侧数组中寻找最小的 k-m 个数
            partitionArray(arr, m + 1, hi, k);
        }
    }

    // partition 函数和快速排序中相同
    public int partition(int[] arr, int left, int right) {

        int less = left - 1;
        int more = right;
        int index = left;
        while (index < more) {
            if (arr[index] < arr[right]) {
                //其实就是less和index后移一位，没有交换
                swap(arr, ++less, index++);
            } else if (arr[index] > arr[right]) {
                swap(arr, --more, index);
            } else {
                index++;
            }
        }
        //more之后的元素（包括more）都比right大，交换后，那个参考值现在就是more的位置
        swap(arr, more, right);
        return more;
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0) {
            return new int[k];
        }
        int len = arr.length;
        if (len == k) {
            return arr;
        }
        int[] heap = new int[k];
        // 对arr中前k个元素建立堆
        for (int i = 0; i < k; i++) {
            heap[i] = arr[i];
        }
        buildHeap(heap);

        //对后面较小的树建堆
        for(int i = k; i < len ; i++){
            if(arr[i] < heap[0]){
                heap[0] = arr[i];
                heapify(heap,0);
            }
        }
        //返回这个堆
        return heap;
    }

    //建堆。对于一个还没维护过的堆，从他的最后一个节点的父节点开始进行调整。
    private void buildHeap(int[] nums) {
        //最后一个节点
        int lastNode = nums.length - 1;
        //记住：父节点 = (i - 1) / 2  左节点 = 2 * i + 1  右节点 = 2 * i + 2;
        //最后一个节点的父节点
        int startHeapify = (lastNode - 1) / 2;
        while (startHeapify >= 0) {
            //不断调整建堆的过程
            heapify(nums, startHeapify--);
        }
    }

    //调整大顶堆的过程
    private void heapify(int[] nums, int i) {
        //和当前节点的左右节点比较，如果节点中有更大的数，那么交换，并继续对交换后的节点进行维护
        int len = nums.length;
        if (i >= len)
            return;
        //左右子节点
        int c1 = ((i << 1) + 1), c2 = ((i << 1) + 2);
        //假定当前节点最大
        int max = i;
        //如果左子节点比较大，更新max = c1;
        if (c1 < len && nums[c1] > nums[max]) max = c1;
        //如果右子节点比较大，更新max = c2;
        if (c2 < len && nums[c2] > nums[max]) max = c2;
        //如果最大的数不是节点i的话，那么heapify(nums, max)，即调整节点i的子树。
        if (max != i) {
            swap(nums, max, i);
            //递归处理
            heapify(nums, max);
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
    }

}
