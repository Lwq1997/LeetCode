package com.lwq.primary_algorithm.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Lwq
 * @Date: 2018/8/24 14:44
 * @Version 1.0
 * @Describe 给定两个数组，编写一个函数来计算它们的交集。（如果交集重复，请完整输出）
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */
public class intersect2 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,9,5};
        int[] nums2 = {9,4,9,8,4};
        intersect(nums1,nums2);
    }

    /**
     * 普通方法
     * @param nums1
     * @param nums2
     * @return nums1和nums2的交集
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for(int num:nums1){
            list1.add(num);
        }
        for(int num:nums2){
            if(list1.contains(num)){
                list2.add(num);
                list1.remove(Integer.valueOf(num));
            }
        }
        int[] res = new int[list2.size()];
        int i = 0;
        for (int num : list2) {
            res[i++] = num;
        }
        return res;
    }

    public int[] intersect_2(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2)
                .boxed()
                .filter(num -> {
                    if (list1.contains(num)) {
                        list1.remove(num);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        int[] res = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            res[i] = list2.get(i);
        }
        return res;
    }

    /**
     *数组中的元素可以重复
     * @param nums1
     * @param nums2
     * @return
     */
//    用Map来建立nums1中字符和其出现个数之间的映射,
//    然后遍历nums2数组，如果当前字符在Map中的个数大于0，
//    则将此字符加入结果res中，然后Map的对应值自减1。
    public static int[] intersect1(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> tmp = new ArrayList<>();
         for(int i = 0; i < nums1.length ; i++){
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        for(int j = 0; j <nums2.length ; j++){
            if(map.containsKey(nums2[j])&&map.get(nums2[j])!=0){
                tmp.add(nums2[j]);
                map.put(nums2[j],map.get(nums2[j])-1);
            }
        }
        int[] res = new int[tmp.size()];
        int i = 0;
        for (Integer e : tmp)
            res[i++] = e;
        return res;
    }

    /**
     * 排序数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> tmp = new ArrayList<>();
        int j = 0;
        int i = 0;
        while (i<nums1.length&&j<nums2.length){
            if(nums1[i]<nums2[j]){
                i++;
            }else if(nums1[i]>nums2[j]){
                j++;
            }else {
                tmp.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[tmp.size()];
        for (int k = 0; k < result.length; k++) {
            result[k] = tmp.get(k);
        }
        return result;
    }
}
