package com.lwq;

import com.lwq.TreeNode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 *
 */
public class LeetCode124 {

    private int maximum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        helper(root);

        return maximum;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果左右子树返回的最大路径值小于 0
        // 直接将值设为 0，也就是不考虑对应的路径
        int leftMax = Math.max(0, helper(root.left));
        int rightMax = Math.max(0, helper(root.right));

        // (root.val + leftMax + rightMax) 代表以当前节点为跟的子数的路径值。
        // 每个节点都算一下，如果以自身为根，他们的路径和是多少，取一个最大的。
        // 如果整个树都是负值，在这里结果一定是maximum自身，会丢弃 leftMax + rightMax，每个节点都会计算一下，最后返回的就是最大的那个节点
        maximum = Math.max(root.val + leftMax + rightMax, maximum);

        // 当前节点要给其他的节点贡献出，从当前节点向下最大的一条路径
        return Math.max(leftMax + root.val, rightMax + root.val);
    }
}
