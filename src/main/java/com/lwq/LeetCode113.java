package com.lwq;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */

public class LeetCode113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayDeque<Integer> path = new ArrayDeque<>();
        dfs(root, sum, path, res);
        return res;
    }

    private void dfs(TreeNode root, int sum, ArrayDeque<Integer> path, ArrayList<List<Integer>> res) {
        if (root == null) {
            // 必须要return
            return;
        }
        // 把当前节点放到路径上
        sum -= root.val;
        path.addLast(root.val);

        // 判断是否是一条正确的路径
        if (sum == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
            // 这里要回退一个节点
            path.removeLast();
            // 必须return
            return;
        }
        // 走到这里说明不是正确的路径，继续往下
        dfs(root.left, sum, path, res);
        dfs(root.right, sum, path, res);

        // 这里也要回退一个节点
        path.removeLast();
    }
}
