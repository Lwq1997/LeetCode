package com.lwq.primary_algorithm.string;

/**
 * @Author: Lwq
 * @Date: 2018/8/25 16:31
 * @Version 1.0
 * @Describe  颠倒整数
 */
public class reverse {
    public static void main(String[] args) {
        System.out.println(reverse2(-789));
    }
    public static int reverse(int x) {
        String s = String.valueOf(x);
        StringBuilder builder = new StringBuilder();
        for(int i = s.length()-1;i>-1;i--){
            if(s.charAt(i)=='-'){
                builder.insert(0,'-');
            }else {
                builder.append(s.charAt(i));
            }
        }
        try {
            return Integer.parseInt(builder.toString());
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static int reverse2(int x) {
        int result = 0;
        while (x!=0){
            // 因为都是用int型的，如果超出了范围，
            // 其除以10的结果就不会跟之前的结果一致，
            // 通过这点也可以进行区分
            int tmp = result * 10 + x % 10;
            if (tmp / 10 != result) {
                return 0;
            }
            result = tmp;
            x = x / 10;
        }
        return result;
    }
}
