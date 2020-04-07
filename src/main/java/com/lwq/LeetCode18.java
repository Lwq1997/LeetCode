package com.lwq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class LeetCode18 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0};
        int target = 0;
        System.out.println(fourSum(nums, target));
    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
                //最小的4个数都>target
            }
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                continue;
                //最大的3个数+自身<target
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                    //最小的4个数都>target
                }
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) {
                    continue;
                    //最大的2个数+自身<target
                }
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int tmp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (tmp == target) {
                        List<Integer> tmpList = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        res.add(tmpList);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                            //去重
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                            //去重
                        }
                        left++;
                        right--;
                    } else if (tmp < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }

}