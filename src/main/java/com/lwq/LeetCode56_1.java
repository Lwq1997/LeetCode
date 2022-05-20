package com.lwq;

import com.lwq.bitoperation.PrintIntBinary;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 */
public class LeetCode56_1 {
    public static void main(String[] args) {
        singleNumbers(new int[]{4, 15, 4, 9});
    }

    public static int[] singleNumbers(int[] nums) {
        // 用于将所有的数字异或起来
        int k = 0;

        // k中只有两个位置有1
        for (int num : nums) {
            k ^= num;
        }
        //此时k等于 a^b


        // 取出k中最低位的1
        int mask = 1;
        while ((k & mask) == 0) {
            mask <<= 1;
        }

        int a = 0;
        int b = 0;

        for (int num : nums) {
            // mask只有和两个不唯一的数中的其中一个进行与计算才等于0
            if ((num & mask) == 0) {
                //能进入这个分支的数字，说明他的第X位（ 具体X 就等于 mask 中的1的位置 ）肯定都为0
                // 相同的数字自己^自己 = 0了
                // 不同的数字被0疑惑，终于异或出来了
                // 整个思路等价于在一组重复数组（其他数字都是2次，只有某一个数字出现了一次）找到那一个重复数字。这里把整个原始数组分为两类，X位为1的，X位不为1的
                a ^= num;
                new PrintIntBinary().printInt(a);
            } else {
                b ^= num;
            }
        }
        System.out.println("=================");
        new PrintIntBinary().printInt(4);
        new PrintIntBinary().printInt(k);
        new PrintIntBinary().printInt(mask);
        new PrintIntBinary().printInt(a);
        new PrintIntBinary().printInt(b);
        return new int[]{a, b};

    }

}
