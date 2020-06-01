package com.lwq;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 */
public class LeetCode144 {
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void preOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            // 	先右再左
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    class Solution {
        ArrayList<Integer> list = new ArrayList<>();
        //第一要素：明确你这个函数想要干什么
        //函数功能：进行先序遍历二叉树
        public List<Integer> preorderTraversal(TreeNode root) {
            //第二要素：寻找递归结束条件
            if(root == null)
                return list;
            //第三要素：找出函数的等价关系式
            list.add(root.val);//中

            if(root.left != null)//左
                preorderTraversal(root.left);

            if(root.right != null)//右
                preorderTraversal(root.right);

            return list;
        }
    }
}
