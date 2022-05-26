package com.lwq;

public class BitMap {
    public static void main(String[] args) {

        int[] arr = new int[10];//32*10=320
        //arr[0]  0 - 31
        //arr[1]  32 - 63
        //arr[2]  64 - 95

        //第178位
        int index = 178;
        int numIndex = index / 32;//第178位在第几个数组中
        int bitIndex = index % 32;//第178位在第几位

        int s = ((arr[numIndex] >> (bitIndex)) & 1);//第178位的值

        // 请把178位的状态改为1
        arr[numIndex] = arr[numIndex] | (1 << bitIndex);

        // 请把178位的状态改为0
        arr[numIndex] = arr[numIndex] & (~(1 << bitIndex));
        System.out.println(s);

    }
}
