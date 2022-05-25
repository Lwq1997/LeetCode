package com.lwq.violencerecursive;

public class Hanoi {
    public static void main(String[] args) {
        int n = 5;
        move(n, '左', '中', '右');
    }

    private static void move(int n, char start, char other, char end) {
        if (n == 1) {
            System.out.println("移动" + n + "个圆盘从" + start + "到" + end);
            return;
        }
        // 将n-1个圆盘移动到中间
        move(n - 1, start, end, other);
        System.out.println("移动" + n + "个圆盘从" + start + "到" + end);
        // 将n-1个圆盘移动到右边
        move(n - 1, other, start, end);
    }
}
