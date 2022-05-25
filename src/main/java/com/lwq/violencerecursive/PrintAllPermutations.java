package com.lwq.violencerecursive;

import java.util.ArrayList;

/**
 * 打印字符串的所有排列,不能有重复结果
 */
public class PrintAllPermutations {
    public static void main(String[] args) {
        String str = "aabb";
        char[] chars = str.toCharArray();
        ArrayList<String> res = new ArrayList<>();
        printPermutations(chars, 0, res);
        for (String re : res) {
            System.out.println(re);
        }
    }

    /**
     * i位置上开始尝试str[i]之后的所有字符
     * str[0,i-1]已经排好序,是之前做的选择
     * @param chars
     * @param i
     * @param res
     */
    private static void printPermutations(char[] chars, int i, ArrayList<String> res) {
        boolean[] visited = new boolean[26];
        if (i == chars.length) {
            res.add(new String(chars));
            return;
        }
        for (int j = i; j < chars.length; j++) {
            if (!visited[chars[j] - 'a']) {
                visited[chars[j] - 'a'] = true;
                swap(chars, i, j);//交换
                printPermutations(chars, i + 1, res);
                swap(chars, i, j);//还原
            }
        }
    }

    /**
     * 交换数组中两个元素
     *
     * @param chars
     * @param i
     * @param j
     */
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
