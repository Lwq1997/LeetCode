package com.lwq.primary_algorithm.array;


/**
 * @author Lwq
 * @create 2018-08-20 10:30
 * @desc 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 **/
/**
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
/**
 * 采用两个标记点 number 和 i ，number记录不重复元素的位置，
 * i从number的下一个开始遍历数组，如果i位置的数字等于number位置的数字，
 * 说明该数字重复出现，不予处理；如果i位置的数字不等于number位置的数字，
 * 说明该数字没有重复，需要放到l的下一位置，并使number加1。
 */
public class removeduplicates {
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,2,4,4,4,5,5,5};
        int res = removeDuplicates(arr);
        System.out.println(res);
        for(int i = 0; i < res ; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static int removeDuplicates(int[] arrs){
        if(arrs.length==0){
            return 0;
        }
        int number = 0;
        for(int i = number; i < arrs.length ; i++){
            if(arrs[i] != arrs[number]){
                number++;
                arrs[number] = arrs[i];
            }
        }
        return number+1;
    }
}
