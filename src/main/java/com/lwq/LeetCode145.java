package com.lwq;

import java.util.*;

/**
 *给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 *
 *
 * 前序遍历：144
 * 中序遍历：94
 */
public class LeetCode145 {

    /**
     * 后序遍历，递归模式
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_01(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs(res,root);
        return res;
    }

    void dfs(List<Integer> res, TreeNode root) {
        if(root==null) {
            return;
        }
        //按照 左-右-打印的方式遍历
        dfs(res,root.left);
        dfs(res,root.right);
        res.add(root.val);
    }

    /**
     * 后序遍历，迭代模式
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_02(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }
        return res;
    }

    public List<Integer> postorderTraversal_03(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        //这一步完成根节点=》左节点=》右节点的转换--前序遍历
        //这一步完成根节点=》右节点=》左节点的转换
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.right;
            }
            TreeNode node = stack.pop();
            if (node.left != null) {
                cur = node.left;
            }
        }
        //这一步完成左节点=》右节点=》跟节点的转换
        Collections.reverse(res);
        return res;
    }

}
