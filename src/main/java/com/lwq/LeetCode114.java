package com.lwq;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class LeetCode114 {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);

        // 把当前节点的右节点，放到当前节点的左节点的右边
        TreeNode temp = root.right;
        // 把当前节点的左节点，变成当前节点的右节点
        root.right = root.left;
        root.left = null;
        // 移动当前节点到当前节点的最右节点
        while (root.right != null){
            root = root.right;
        }
        // 把之前节点的右节点，接起来
        root.right = temp;
    }
}
