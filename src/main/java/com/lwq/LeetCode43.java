package com.lwq;


/**
 * 字符串相乘
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class LeetCode43 {
    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "45";

        System.out.println(multiply(str1, str2));
        System.out.println(multiply1(str1, str2));
        System.out.println(multiply2(str1, str2));
    }

    private static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        StringBuilder num3 = new StringBuilder(num1).reverse();
        StringBuilder num4 = new StringBuilder(num2).reverse();

        System.out.println(num1 + "====" + num3);
        System.out.println(num2 + "====" + num4);

        int[] res = new int[num1.length() + num2.length()];
        for (int i = 0; i < num3.length(); i++) {
            for (int j = 0; j < num4.length(); j++) {
                res[i + j] += (num3.charAt(i) - '0') * (num4.charAt(j) - '0');
            }
        }

        for (int i = 0; i < res.length; i++) {
            if (res[i] > 9) {
                int tmp = res[i];
                res[i] = tmp % 10;
                res[i + 1] += tmp / 10;
            }
        }

        StringBuilder str = new StringBuilder();
        int length = res[num1.length() + num2.length() - 1] == 0 ? num1.length() + num2.length() - 2 : num1.length() + num2.length() - 1;
        while (length >= 0) {
            str.append(res[length]);
            length--;
        }
        return str.toString();

    }

    /**
     * - 乘数 num1 位数为 M，被乘数 num2 位数为 N， num1 x num2 结果 res 最大总位数为 M+N
     * - num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >= 0; i--) {
            // 这是num1第i位的值
            int n1 = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0; j--) {
                // 这是num2第j位的值
                int n2 = num1.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }

    /**
     * 遍历 num2 每一位与 num1 进行相乘，将每一步的结果进行累加。
     * <p>
     * 注意：
     * <p>
     * - num2 除了第一位的其他位与 num1 运算的结果需要 补0
     * - 计算字符串数字累加
     * <p>
     * **复杂度分析**
     * <p>
     * - 时间复杂度：*O*(*M N*)。M,N分别为 `num1` 和 `num2` 的长度。
     * - 空间复杂度：*O*(M+N)。用于存储计算结果。
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        //计算结果
        String res = "0";

        //num2的每一位和num1相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;

            //这是每一位和num1相乘的结果
            StringBuilder temp = new StringBuilder();

            //除了num2的最后一位和num1相乘不用补0之外，其他每一位都要补0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append("0");
            }

            // 这是num2第i位的值
            int n2 = num2.charAt(i) - '0';

            //开始相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                // 这是num1第i位的值
                int n1 = j >= 0 ? num1.charAt(j) - '0' : 0;
                int tmp = n1 * n2 + carry;
                carry = tmp / 10;
                temp.append(tmp % 10);
            }
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }


    private static String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) {
            res.append(1);
        }
        return res.reverse().toString();
    }

}
