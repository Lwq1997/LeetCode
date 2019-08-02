package com.lwq.primary_algorithm.tree;

/*
验证二叉搜索树
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
 */

import java.util.ArrayList;
import java.util.List;

public class isValidBST {
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

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(isValidBST(a));
        System.out.println(isValidBST(a));
    }

    public static boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        search(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static void search(TreeNode root, List<Integer> list) {
        if (root != null) {
            search(root.left, list);
            list.add(root.val);
            search(root.right, list);
        }
    }

    public static boolean isValidBST01(TreeNode root) {
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validBST(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val <= minValue || root.val >= maxValue) {
            return false;
        }
        return validBST(root.left, minValue, root.val) && validBST(root.right, root.val, maxValue);
    }
}
