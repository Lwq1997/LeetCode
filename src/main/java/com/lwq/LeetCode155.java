package com.lwq;

import java.util.Stack;

/**
 *设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class LeetCode155 {
    class MinStack {
        Stack<Integer> data  = null;
        Stack<Integer> help  = null;


        /** initialize your data structure here. */
        public MinStack() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            // 数据栈和辅助栈一定会增加元素
            data.add(x);
            if (help.isEmpty() || help.peek() >= x) {
                help.add(x);
            } else {
                help.add(help.peek());
            }
        }

        public void pop() {
            // 两个栈都得 pop
            if (!data.isEmpty()) {
                help.pop();
                data.pop();
            }
        }
        public int top() {
            if(!data.isEmpty()){
                return data.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

        public int getMin() {
            if(!help.isEmpty()){
                return help.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
