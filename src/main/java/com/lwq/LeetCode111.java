package com.lwq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 */

public class LeetCode111 {
    public static void main(String[] args) {

    }

    /**
     * 使用BFS算法来解决
     * 起点是root跟节点
     * 终点是靠近跟节点的叶子结点 (cur.left == null && cur.right == null)
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                /* 判断是否到达终点 */
                if (curr.left == null && curr.right == null) {
                    return depth;
                }
                /* 将 cur 的相邻节点加入队列 */
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }

    /**
     * 使用递归实现
     * @param root
     * @return
     */
    public int getminDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getminDepth(root.left) + 1;
        int rightDepth = getminDepth(root.right) + 1;
        return Math.min(leftDepth, rightDepth);

    }
}
