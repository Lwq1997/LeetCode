package com.lwq.primary_algorithm.string;

import org.omg.CORBA.INTERNAL;

public class longestCommonPrefix {
    public static void main(String[] args) {

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < strs.length; i++) {
            min = Math.min(min, strs[i].length());
        }

        int[] commons = new int[strs.length-1];
        for (int i = 0; i < strs.length-1; i++) {
            for(int j = 0 ; j < min; j++){
                if(strs[i].charAt(j) == strs[i+1].charAt(j)){
                    commons[i]++;
                }
                else {
                    break;
                }
            }
        }

        int longest = Integer.MAX_VALUE;
        for(int i = 0; i < commons.length ; i++){
            longest = Math.min(longest,commons[i]);
        }

        return strs[0].substring(0,longest);
    }

    public static String longestCommonPrefix01(String[] strs) {
        int count = strs.length;
        String prefix = "";
        if(count != 0){
            prefix = strs[0];
        }
        for(int i = 0; i < strs.length; i++){
            while (!strs[i].startsWith(prefix)){
                prefix = prefix.substring(0,prefix.length()-1);
            }
        }
        return prefix;
    }
}

