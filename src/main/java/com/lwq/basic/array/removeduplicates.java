package com.lwq.basic.array;



import org.junit.Test;
/**
 * @author Lwq
 * @create 2018-08-20 10:30
 * @desc 从排序数组中删除重复项
 **/

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
    @Test
    public static int removeDuplicates(int[] arrs){
        if(arrs.length==0){
            return 0;
        }
        int number = 0;
        for(int i = 0; i < arrs.length ; i++){
            if(arrs[i]!=arrs[number]){
                number++;
                arrs[number]=arrs[i];
            }
        }
        number+=1;
        return number;
    }
}
