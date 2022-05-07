package com.lwq;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 */
public class LeetCode283 {
    public static void main(String[] args) {
        LeetCode283 leetCode283 = new LeetCode283();
        int[] nums = {0,1,0,3,12};
        leetCode283.moveZeroes01(nums);
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
    }

    public void moveZeroes(int[] nums) {
        int i,j = 0;
        for(i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                // 把所有的非0都挪到j的位置
                nums[j++] = nums[i];
            }
        }
        // 最后填充0
        while (j < nums.length){
            nums[j++] = 0;
        }
    }

    public void moveZeroes01(int[] nums) {
        if(nums == null){
            return;
        }
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                //当前元素!=0，就把其交换到左边，等于0的交换到右边
                swap(nums,i,j++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
