package com.lwq;

public class LeetCode1122 {
    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] aarr2 = {2,1,4,3,9,6};
        int[] ints = relativeSortArray(arr1, aarr2);
    }
    /**
     * 第一个for循环累加arr1数组中每个数字的个数。
     * 第二个for循环直接在arr1上从0开始位置添加arr2中顺序的值，并且将该值累计数-1(保证相同数量的值)
     * 第三个for循还添加arr2中没有的值。
     * 最后从arr2中没有的值的位置之后使用Arrays.sort() 直接排序 返回arr1;
     *
     * @param arr1
     * @param arr2
     * @return
     */

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] tmp = new int[1001];
        for (int num : arr1) {
            tmp[num]++;
        }
        // arr1中索引的位置 也是arr2中含有的数字在arr1中的最大长度
        int pos = 0;
        for (int num : arr2) {
            // 判断一下tmp中该下标是不是大于0,如果大于，就持续输出该位置的数据
            while (tmp[num] > 0) {
                // 把arr2中的数字按顺序排序到arr1
                arr1[pos++] = num;
                tmp[num]--;
            }
        }
        // 剩下的数字排序，肯定是正序
        for (int i = 0; i < 1001; ++i) {
            while (tmp[i] > 0) {
                arr1[pos++] = i;
                tmp[i]--;
            }
        }
        return arr1;
    }
}
