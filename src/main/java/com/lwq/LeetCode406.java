package com.lwq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 *
 */
public class LeetCode406 {
    public static void main(String[] args) {
        int[][] persons = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        System.out.println(reconstructQueue(persons));

        LinkedList<Integer> objects = new LinkedList<>();
        objects.add(0,1);
        objects.add(0,2);
        objects.add(0,3);
        System.out.println(objects.get(0));
        System.out.println(objects.get(1));
        System.out.println(objects.get(2));
    }


    public static int[][] reconstructQueue(int[][] persons) {
        // 对输入数组基于身高排序（倒序）
        // 如果身高相同，基于人数排序（正序）
        Arrays.sort(persons, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return b[0] - a[0];
                }
                return a[1] - b[1];
            }
        });
        for(int i = 0; i < persons.length; i++){
            System.out.println(Arrays.toString(persons[i]));
        }

        List<int[]> res = new LinkedList<>();
        for (int[] person : persons) {
            // 基于人数将元素插入到指定位置即可
            res.add(person[1],person);
        }
        System.out.println("********************");
        for(int i = 0; i < persons.length; i++){
            System.out.println(Arrays.toString(res.get(i)));
        }


        return res.toArray(new int[persons.length][2]);
    }
}
