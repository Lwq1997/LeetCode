package com.lwq.sort;


/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        System.out.println(getDigit(1,2));
        sort(new int[]{1, 4, 6, 2, 6, 2, 6, 7, 1,123,512,1,213,12,124,345121,5,4533, 4, 7, 8, 3, 7, 8, 3, 100});
    }

    private static void sort(int[] nums) {
        int digit = getMaxDigit(nums);
        radixSort(nums, 0, nums.length - 1, digit);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void radixSort(int[] nums, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        //有多少个数字就准备多少空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { //有多少个位数就循环多少次
            //10个桶
            int[] count = new int[radix];
            //把数据的放到桶中，桶的下标就是数字的每一位，桶的值就是数字的个数
            //count[0],当前位置d的是0的个数
            //count[1],当前位置d的是1的个数
            //count[2],当前位置d的是2的个数
            for (i = L; i <= R; i++) {
                j = getDigit(nums[i], d);
                count[j]++;
            }
            // 根据上面词频桶，生成前缀和桶，桶的下标就是数字的每一位，桶的值就是小于等于当前数字的数字的个数
            for (i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }
            // 根据桶，把数据放到桶中
            //此时必须从右向左，因为刚才进桶是从左往右，所以此时的数据是从右往左放的
            for (i = R; i >= L; i--) {
                j = getDigit(nums[i], d);
                bucket[count[j] - 1] = nums[i];
                count[j]--;
            }
            // 把桶中的数据放回原数组
            for (i = L, j = 0; i <= R; i++, j++) {
                nums[i] = bucket[j];
            }
        }
    }

    /**
     * 找到num位于第d位的数字
     *
     * @param x
     * @param d
     * @return
     */
    private static int getDigit(int x, int d) {
        return (x / (int) Math.pow(10, d - 1)) % 10;
    }

    private static int getMaxDigit(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int res = 0;
        while (max > 0) {
            max /= 10;
            res++;
        }
        return res;
    }
}
