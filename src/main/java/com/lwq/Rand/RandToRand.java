package com.lwq.Rand;

public class RandToRand {
    /**
     * 通过这个函数获得1-7的随机数，假设有一个库函数是f1()，这个函数返回1-5的随机数
     *
     * @param args
     */
    public static void main(String[] args) {
        int times = 10000000;
        int[] nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f1()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        System.out.println("===========================");
        nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f2()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        System.out.println("===========================");
        nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f4()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        System.out.println("===========================");
        nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f5()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        System.out.println("===========================");
        nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f6()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }

        System.out.println("===========================");
        System.out.println("===========================");
        System.out.println("===========================");
        System.out.println("===========================");
        nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f8()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        System.out.println("===========================");
        nums = new int[10];
        for (int i = 0; i < times; i++) {
            nums[f9()]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
    }

    /**
     * 这个函数返回1-5的随机数
     *
     * @return
     */
    private static int f1() {
        //先出一个0-4的随机数
        int num = (int) (Math.random() * 5);
        return num + 1;
    }

    /**
     * 这个函数返回0-1的随机数
     *
     * @return
     */
    private static int f2() {
        int ans = 0;
        //只要遇到3就重新去获取随机数，这样结果中永远不会有3
        do {
            ans = f1();
        } while (ans == 3);
        //获得4，5的概率是1/2
        //或者1，2的概率是1/2
        return ans < 3 ? 0 : 1;
    }

    /**
     * 这个函数返回0-7的随机数
     *
     * @return
     */
    private static int f3() {
        return (f2() << 2) + (f2() << 1) + (f2() << 0);
    }

    /**
     * 这个函数返回1-7的随机数
     *
     * @return
     */
    private static int f4() {
        int ans = 0;
        //只要遇到0就重新去获取随机数，这样结果中永远不会有0
        do {
            ans = f3();
        } while (ans == 0);
        return ans;
    }

    /**
     * 这个函数返回0-6的随机数
     *
     * @return
     */
    private static int f5() {
        int ans = 0;
        //只要遇到0就重新去获取随机数，这样结果中永远不会有0
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    /**
     * 这个函数返回1-7的随机数
     *
     * @return
     */
    private static int f6() {
        return f5() + 1;
    }

    /**
     * 这个函数非等概率返回0和1
     *
     * @return
     */
    private static int f8() {
        return Math.random() < 0.888 ? 0 : 1;
    }

    /**
     * 这个函数等概率返回0和1
     *
     * @return
     */
    private static int f9() {
        int ans = 0;
        do {
            //00 重做
            //01 返回，0
            //10 返回，1
            //11 重做
            ans = f8();
        } while (ans == f8());
        return ans;
    }
}
