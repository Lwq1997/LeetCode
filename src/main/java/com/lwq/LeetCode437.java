package com.lwq;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class LeetCode437 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);
        root1.right.right.right = new TreeNode(4);
        root1.right.right.right.right = new TreeNode(5);
        System.out.println(pathSum(root1,3));
    }

    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 算出以当前节点为起始点，正确的路径有几条
        int curr = countPath(root, sum);
        // 算出以当前节点的左孩子为起始点，正确的路径有几条，这里必须使用pathSum这个方法，因为他除了需要算当前的左孩子，还有计算当前左孩子的左孩子
        int left = pathSum(root.left, sum);
        // 算出以当前节点右孩子为起始点，正确的路径有几条，这里必须使用pathSum这个方法，因为他除了需要算当前的右孩子，还有计算当前右孩子的右孩子
        int right = pathSum(root.right, sum);

        return curr + left + right;
    }

    private static int countPath(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum -= root.val;
        int result = sum == 0 ? 1 : 0;
        // 这里就是一致往下算，直到叶子结点
        return result + countPath(root.left, sum) + countPath(root.right, sum);
    }
}
