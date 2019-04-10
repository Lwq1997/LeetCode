package com.lwq.primary_algorithm.array;

import java.util.*;

/**
 * @Author: Lwq
 * @Date: 2018/8/24 14:44
 * @Version 1.0
 * @Describe  给定两个数组，编写一个函数来计算它们的交集。
 */
public class intersect {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        intersect1(nums1,nums2);
    }

    /**
     * 普通方法(数组中的元素不能重复)
     * @param nums1
     * @param nums2
     * @return nums1和nums2的交集
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                }
            }
        }
        int[] nums = new int[set.size()];
        int i = 0;
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            nums[i++] = (int) iterator.next();
        }
        return nums;
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
