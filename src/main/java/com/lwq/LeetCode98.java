package com.lwq;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class LeetCode98 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(6);
        root.left = node1;
//        root.right = node2;
//        node2.left = node3;
//        node2.right = node4;
        LeetCode98 leetCode98 = new LeetCode98();
        System.out.println(leetCode98.isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
//        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return isBST(root);
    }

    private boolean validBST(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val <= minValue || root.val >= maxValue) {
            return false;
        }
        return validBST(root.left, minValue, root.val) && validBST(root.right, root.val, maxValue);
    }

    int prevalue = Integer.MIN_VALUE;

    public boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isLeftBST = isBST(root.left);
        if (!isLeftBST) {
            return false;
        }
        if (prevalue >= root.val) {
            return false;
        } else {
            prevalue = root.val;
        }
        return isBST(root.right);
    }

}
