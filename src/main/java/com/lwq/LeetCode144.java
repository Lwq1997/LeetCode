package com.lwq;

import java.util.*;

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
 *
 * 中序遍历：94
 * 后序遍历：145
 */
public class LeetCode144 {
    public static void main(String[] args) {
        //测试用例
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        List<Integer> integers = new LeetCode144().preOrderIteration_01(head);
        System.out.println(integers);
    }

    /**
     * 前序遍历，递归模式
     * @param root
     * @return
     */
    public List<Integer> preOrderIteration_01(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs(res,root);
        return res;
    }

    void dfs(List<Integer> res, TreeNode root) {
        if(root==null) {
            return;
        }
        //按照 打印—左-右的方式遍历
        res.add(root.val);
        dfs(res,root.left);
        dfs(res,root.right);
    }

    public List<Integer> preOrderIteration_02(TreeNode head) {
        ArrayList<Integer> res = new ArrayList<>();
        if (head == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // 	先右再左
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
}
