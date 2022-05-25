package com.lwq.violencerecursive;

/**
 * 1代表A
 * 2代表B
 * 3代表C
 * 4代表D
 * 5代表E
 * 。。。。
 * <p>
 * 给你一个数字，输出对应的字母可能出现的次数
 * 比如111，可能出现AAA，AK，KA
 */
public class ConvertToLetterString {
    public static void main(String[] args) {
        String num = "1111";
        char[] chars = num.toCharArray();
        Integer result = convertToLetterString(chars, 0);
        System.out.println(result);
    }

    /**
     * 这个函数是返回i位置开始的可能新，默认i-1位置已经决定了
     *
     * @param chars
     * @param i
     * @return
     */
    private static Integer convertToLetterString(char[] chars, int i) {
        if (i == chars.length) {
            return 1;
        }
        if (chars[i] == '0') {
            //如果数组中出现0，只能组成和10或者20，因为i单独到了0，说明i-1位置已经决定了，此时已经无效了，截断
            return 0;
        }
        if (chars[i] == '1') {
            //i位置决定了，继续往下走
            int res = convertToLetterString(chars, i + 1);
            if (i + 1 < chars.length) {
                res += convertToLetterString(chars, i + 2);
            }
            return res;
        }
        if (chars[i] == '2') {
            //i位置决定了，继续往下走
            int res = convertToLetterString(chars, i + 1);
            if (i + 1 < chars.length && chars[i + 1] >= '0' && chars[i + 1] <= '6') {
                //如果超过26.则不能连起来处理
                res += convertToLetterString(chars, i + 2);
            }
            return res;
        }
        //如果是其他数字，则只能自己组成，继续往下走，不能和i+1位置组合
        return convertToLetterString(chars, i + 1);
    }
}
