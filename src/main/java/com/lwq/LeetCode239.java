package com.lwq;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值所构成的数组。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class LeetCode239 {
    public static void main(String[] args) {
        int[] ints = maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    //时间复杂度的话是 O(nk)。
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        int[] win = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            //找到每一个滑动窗口的最大值
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            win[i] = max;
        }
        return win;
    }

    //虽然 offer 函数的时间复杂度是 log 级的，但是 remove 是 O(k) ，所以最终的时间复杂度依旧是 O(nk)。
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int len = nums.length;
        int[] result = new int[len - k + 1];
        int index = 0;
        if (len == 0) {
            return nums;
        }

        for (int i = 0; i < len; i++) {
            if (queue.size() > k) {
                //当堆中的数大于k个的时候，移除多余的（i-k）这个数
                queue.remove(nums[i - k]);
            }
            queue.offer(nums[i]);
            //更新 result，只有从i>=k-1之后才会有输出
            if (i >= k - 1) {
                result[index++] = queue.peek();
            }
        }
        return result;
    }

    /**
     * 如果当前元素比队列的最后一个元素大，那么就将最后一个元素出队，重复这步直到当前元素小于队列的最后一个元素或者队列为空。进行下一步。
     * 如果当前元素小于等于队列的最后一个元素或者队列为空，那么就直接将当前元素入队。
     * 按照上边的方法添加元素，队列中的元素就刚好是一个单调递减的序列，而最大值就刚好是队头的元素。
     * 当滑动到下一个窗口的时候，需要跑断一下队列头部的元素是不是我们想移除的那个元素
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     *
     * 解释过程中队列中都是具体的值，方便理解，具体见代码。
     * 初始状态：L=R=0,队列:{}
     * i=0,nums[0]=1。队列为空,直接加入。队列：{1}
     * i=1,nums[1]=3。队尾值为1，3>1，弹出队尾值，加入3。队列：{3}
     * i=2,nums[2]=-1。队尾值为3，-1<3，直接加入。队列：{3,-1}。此时窗口已经形成，L=0,R=2，result=[3]
     * i=3,nums[3]=-3。队尾值为-1，-3<-1，直接加入。队列：{3,-1,-3}。队首3对应的下标为1，L=1,R=3，有效。result=[3,3]
     * i=4,nums[4]=5。队尾值为-3，5>-3，依次弹出后加入。队列：{5}。此时L=2,R=4，有效。result=[3,3,5]
     * i=5,nums[5]=3。队尾值为5，3<5，直接加入。队列：{5,3}。此时L=3,R=5，有效。result=[3,3,5,5]
     * i=6,nums[6]=6。队尾值为3，6>3，依次弹出后加入。队列：{6}。此时L=4,R=6，有效。result=[3,3,5,5,6]
     * i=7,nums[7]=7。队尾值为6，7>6，弹出队尾值后加入。队列：{7}。此时L=5,R=7，有效。result=[3,3,5,5,6,7]
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int len = nums.length;
        int[] result = new int[len - k + 1];
        int index = 0;
        if (len == 0) {
            return nums;
        }
        for(int i = 0; i < len; i++){
            if(i >= k){
                //当滑动到下一个窗口的时候，需要判断一下队列头部的元素是不是我们想移除的那个元素
                if (deque.peekFirst() == nums[i - k]) {
                    deque.removeFirst();
                }
            }
            //如果当前元素比队列的最后一个元素大，那么就将最后一个元素出队，重复这步直到当前元素小于队列的最后一个元素或者队列为空。进行下一步。
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                deque.removeLast();
            }
            //如果当前元素小于等于队列的最后一个元素或者队列为空，那么就直接将当前元素入队。
            deque.addLast(nums[i]);
            if (i >= k - 1) {
                result[index++] = deque.peekFirst();
            }
        }
        return result;
    }
}