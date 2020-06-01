package com.lwq;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 */
public class LeetCode94 {
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        preOrderRecur(head.left);
        System.out.print(head.val + " ");
        preOrderRecur(head.right);
    }

    public static void inOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                cur = node.right;
            }
        }
    }

    class Solution {
        ArrayList<Integer> list = new ArrayList<>();
        public List<Integer> inorderTraversal(TreeNode root) {
            if(root == null)
                return list;

            if(root.left != null) //左
                inorderTraversal(root.left);

            list.add(root.val); //中

            if(root.right != null)    //右
                inorderTraversal(root.right);
            return list;
        }
    }
}
