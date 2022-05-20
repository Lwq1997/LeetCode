package com.lwq.bitoperation;

public class PrintIntBinary {
    public static void main(String[] args) {
        new PrintIntBinary().printInt(1231231);
        // 负数为取反+1
        new PrintIntBinary().printInt(-1);
        new PrintIntBinary().printInt(Integer.MAX_VALUE);
        new PrintIntBinary().printInt(Integer.MIN_VALUE);
    }

    public void printInt(int num) {
        for (int i = 31; i >= 0; i--) {
            // num 的 第 i 位如果是1  ， 那么 他和 1<<i 相与 结果等于0。 则该位一定为0， 其他位置也为0
            System.out.print((num & (1 << i)) == 0 ? '0' : '1');
        }
        System.out.println();
    }

}
