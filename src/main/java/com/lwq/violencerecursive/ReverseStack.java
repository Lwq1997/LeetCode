package com.lwq.violencerecursive;

import javax.crypto.spec.PSource;
import java.util.Stack;

/**
 * 逆序一个栈
 */
public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
    }

    private static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int stackceil = f(stack);
        reverseStack(stack);
        stack.push(stackceil);
    }

    //返回栈底元素
    private static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        System.out.println("拿出：" + result);
        if (stack.isEmpty()) {
            System.out.println("栈空，返回：" + result);
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            System.out.println("放入：" + result);
            System.out.println("栈：" + stack);
            return last;
        }
    }
}
