package com.lwq.basic.string;

/**
 * @Author: Lwq
 * @Date: 2018/8/25 18:49
 * @Version 1.0
 * @Describe  字符串中的第一个唯一字符
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 */
public class firstUniqChar {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
    public static int firstUniqChar(String s) {
        int help[] = new int[26];
        for(int i = 0; i < s.length();i++){
            help[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++)
            if (help[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
    public static int firstUniqChar1(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < s.length() ; i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(int i = 0; i < s.length();i++){
            if(map.get(s.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }
}
