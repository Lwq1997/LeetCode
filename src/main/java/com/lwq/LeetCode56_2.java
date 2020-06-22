package com.lwq;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 */
public class LeetCode56_2 {
    public int singleNumber(int[] nums) {
        if(nums.length == 0 || nums == null){
            return 0;
        }
        int res = 0;
        for(int i = 0; i < 32; i++){
            // 统计当前位置i中1出现的个数
            int count = 0;
            // index是一个只有当前位为1，其他位为0的数字
            int index = 1 << i;
            for (int num : nums) {
                if((num & index) != 0){
                    count++;
                }
            }
            if(count % 3 == 1){
                // 说明我们要找的那个数字的当前位i是1
                res |= index;
            }
        }
        return res;
    }
}
