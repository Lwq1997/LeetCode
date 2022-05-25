package com.lwq.violencerecursive;

import java.util.ArrayList;

/**
 * 打印字符串的所有子序列
 */
public class PrintAllSubsquences {
    public static void main(String[] args) {
        String str = "abc";
        char[] chars = str.toCharArray();
        printAllSubsquences(chars, 0, new ArrayList<Character>());
    }

    private static void printAllSubsquences(char[] chars, int i, ArrayList<Character> res) {
        if (i == chars.length) {
            printList(res);
            return;
        }
        ArrayList<Character> copyList = copyList(res);
        copyList.add(chars[i]);
        printAllSubsquences(chars, i + 1, copyList);//此时需要i字符
        ArrayList<Character> copyNoInList = copyList(res);
        printAllSubsquences(chars, i + 1, copyNoInList);//此时不需需要i字符
    }

    /**
     * 拷贝list
     *
     * @param list
     * @return
     */
    private static ArrayList<Character> copyList(ArrayList<Character> list) {
        ArrayList<Character> res = new ArrayList<>();
        for (Character character : list) {
            res.add(character);
        }
        return res;
    }

    // 打印list
    private static void printList(ArrayList<Character> res) {
        for (Character c : res) {
            System.out.print(c);
        }
        System.out.println();
    }
}
