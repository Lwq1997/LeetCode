package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class LeetCode129 {
    public int sumNumbers(TreeNode root) {
        return help(root, 0);
    }

    private int help(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int temp = 10 * sum + root.val;
        if (root.left == null && root.right == null) {
            return temp;
        }
        return help(root.left, temp) + help(root.right, temp);
    }

    int sum = 0;

    /**
     * 详细解释
     *
     * @param root
     * @return
     */
    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<>();
        helper(root, list);
        return sum;
    }

    private void helper(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null) {
            addList(list);
        }
        helper(root.left, list);
        helper(root.right, list);
        // 回溯
        list.remove(list.size() - 1);
    }

    private void addList(ArrayList<Integer> list) {
        int temp = 0;
        for (Integer val : list) {
            temp = temp * 10 + val;
        }
        sum += temp;
    }
}
