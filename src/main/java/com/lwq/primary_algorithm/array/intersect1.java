package com.lwq.primary_algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lwq
 * @Date: 2018/8/24 14:44
 * @Version 1.0
 * @Describe 给定两个数组，编写一个函数来计算它们的交集。（如果交集重复，请去重）
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */
public class intersect1 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,9,5};
        int[] nums2 = {9,4,9,8,4};
        intersect(nums1,nums2);
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for(int num:nums1){
            list1.add(num);
        }
        for(int num:nums2){
            if(list1.contains(num)&&!list2.contains(num)){
                list2.add(num);
            }
        }
        int[] res = new int[list2.size()];
        int i = 0;
        for (int num : list2) {
            res[i++] = num;
        }
        return res;
    }
}
