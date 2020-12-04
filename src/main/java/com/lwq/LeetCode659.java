package com.lwq;

import java.util.HashMap;

/**
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 * <p>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *  
 * <p>
 * 示例 2：
 * <p>
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *  
 * <p>
 * 示例 3：
 * <p>
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的数组长度范围为 [1, 10000]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode659 {
    /**
     * 算法思路
     * 首先使用两个哈希 mapnc和tail
     * <p>
     * nc[i]：存储原数组中数字i出现的次数
     * tail[i]：存储以数字i结尾的且符合题意的连续子序列个数
     * 先去寻找一个长度为3的连续子序列 i, i+1, i+2，找到后就将 nc[i], nc[i+1], nc[i+2] 中对应数字消耗1个（即-1），并将 tail[i+2] 加 1，即以 i+2 结尾的子序列个数 +1。
     * 如果后续发现有能够接在这个连续子序列的数字 i+3，则延长以 i+2 为结尾的连续子序列到 i+3，此时消耗 nc[i+3] 一个，由于子序列已延长，因此tail[i+2] 减 1，tail[i+3] 加 1
     * 在不满足上面的情况下
     * 如果 nc[i] 为 0，说明这个数字已经消耗完，可以不管了
     * 如果 nc[i] 不为 0，说明这个数字多出来了，且无法组成连续子序列，所以可以直接返回 false 了
     * 因此，只有检查到某个数时，这个数未被消耗完，且既不能和前面组成连续子序列，也不能和后面组成连续子序列时，无法分割
     * <p>
     * 举例
     * 以 nums=[1,2,3,3,4,4,5] 为例
     * <p>
     * 初始化：nc[1] = 1、nc[2]=1、nc[3]=2、nc[4]=2、nc[5]=1，tail[i]都为0
     * 检查数字 1, nc[1]>0,并且 nc[2]>0,nc[3]>0，因此找到了一个长度为3的连续子序列 nc[1]、nc[2]、nc[3] 各自减一，并 tail[3] 加 1，此时有
     * nc[1] = 0、nc[2]=0、nc[3]=1、nc[4]=2、nc[5]=1
     * tail[3]=1
     * 检查数字 2，发现 nc[2] 为 0，跳过（已经被消耗完了）
     * 检查数字 3，发现 nc[3]>0,但是 tail[2]=0，因此不能接在前面，只能往后看(如果后面组不成，那就返回 false了),实际发现 nc[4]>0,nc[5]>0，因此找到了一个长度为3的连续子序列 nc[3]、nc[4]、nc[5] 各自减一，并 tail[5] 加 1，此时有
     * nc[1] = 0、nc[2]=0、nc[3]=0、nc[4]=1、nc[5]=0
     * tail[3]=1、tail[5]=1
     * 检查第二个数字 3，nc[3]=0，跳过
     * 检查数字 4，nc[4]>0，又有 tail[3]>0，说明有一个以3结尾的连续子序列，因此可以将其延长，所以nc[4]减1，tail[3]减1,tail[4]加1,此时有
     * nc[1] = 0、nc[2]=0、nc[3]=0、nc[4]=0、nc[5]=0
     * tail[3]=0、tail[4]=1、tail[5]=1
     * 检查数字 5，nc[5]=0，跳过
     * 遍历完所有数字，返回 true
     */
    public boolean isPossible(int[] nums) {
        //用一个哈希表统计每个元素出现的次数
        HashMap<Integer, Integer> countNum = new HashMap<>();
        for (int num : nums) {
            countNum.put(num, countNum.getOrDefault(num, 0) + 1);
        }
        //定义一个哈希表记录最长的子序列
        HashMap<Integer, Integer> tail = new HashMap<Integer, Integer>();
        for (int num : nums) {
            int count = countNum.get(num);
            if (count <= 0) {
                continue;
            } else if (tail.getOrDefault(num - 1, 0) > 0) {
                //前面还有数字，可以构成以num结尾的子序列
                countNum.put(num, count - 1);
                //覆盖当前最长的子序列
                tail.put(num - 1, tail.get(num - 1) - 1);
                //当前以num结尾的子序列+1
                tail.put(num, tail.getOrDefault(num, 0) + 1);
            } else if (countNum.getOrDefault(num + 1, 0) > 0 && countNum.getOrDefault(num + 2, 0) > 0) {
                //前面无数字构成子序列后，判断能不能跟后面的构成子序列
                countNum.put(num, count - 1);
                countNum.put(num + 1, countNum.get(num + 1) - 1);
                countNum.put(num + 2, countNum.get(num + 2) - 1);
                //当前以num+2结尾的子序列+1
                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);
            } else {
                //前后不能构成子序列直接返回false
                return false;
            }
        }
        return true;
    }
}
