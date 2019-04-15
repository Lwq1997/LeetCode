package com.lwq.primary_algorithm.string;


/**
 * @Author: Lwq
 * @Date: 2018/8/25 16:04
 * @Version 1.0
 * @Describe  旋转一个字符串
 */

/**
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
 *
 * 示例:
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 */
public class reverseString2 {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseString(s,k));
    }

    public static String reverseString(String s,int k) {
        char[] chs = s.toCharArray();
        int i = 0;
        while (i<chs.length){
            if(i+2*k<chs.length){
                reverse(chs,i,i+k-1);
                i += 2*k;
            }else if(i+k <chs.length){
                //最后还剩的比k多，前k个反转，剩余的输出
                reverse(chs,i,i+k-1);
                break;
            }else if(i+k>=chs.length){
                //最后剩的比k少，直接全部反转
                reverse(chs,i,chs.length-1);
                break;
            }
        }
        s = "";
        for(int j = 0;j < chs.length;j++){
            s+=chs[j];
        }
        return s;
    }

    public static void reverse(char[] chs,int i,int j){
        //start最大是i+j的中点位置，start的对称位置是j-(start-i)
        for(int start = i;start<(j+i+1)/2;start++){
            swap(chs,start,j-(start-i));
        }
    }

    public static void swap(char[] chs,int i,int j){
        char temp=chs[i];
        chs[i]=chs[j];
        chs[j]=temp;
    }
}
