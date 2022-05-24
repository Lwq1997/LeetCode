package com.lwq.greedyalgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小的字典序
 */
public class LowestLexiCography {
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static void main(String[] args) {
        String[] strs = {"b", "ba"};
        System.out.println(lowestLexiCography(strs));
    }

    private static String lowestLexiCography(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String res = "";
        Arrays.sort(strs, new MyComparator());
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }
}
