package com.lwq.primary_algorithm.tree;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。
 */
public class maxDepth {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(9);
        TreeNode c = new TreeNode(20);
        TreeNode d = new TreeNode(15);
        TreeNode e = new TreeNode(7);

        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;

        System.out.println(maxDepth(a));
        System.out.println(minDepth(a));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left) + 1;
        int rightDepth = maxDepth(root.right) + 1;
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }

    public static int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return maxDepth(root.left) + 1;
        }
        if (root.right == null) {
            return maxDepth(root.right) + 1;
        }
        int leftDepth = maxDepth(root.left) + 1;
        int rightDepth = maxDepth(root.right) + 1;
        return leftDepth > rightDepth ? rightDepth : leftDepth;
    }
}
