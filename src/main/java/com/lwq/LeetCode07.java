package com.lwq;

import java.util.HashMap;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 *  
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000
 */
public class LeetCode07 {
    HashMap<Integer, Integer> dic = new HashMap<>();
    int[] po;
    int[] in;
    int pre_inx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        po = preorder;
        in = inorder;
        for (int i = 0; i < inorder.length; i++) {
            dic.put(inorder[i], i);
        }
        return helper(0, inorder.length - 1);
    }

    TreeNode helper(int start, int end) {
        // 说明没有子树了
        if (start > end) {
            return null;
        }
        // 当前根节点的值
        int root_val = po[pre_inx];
        TreeNode node = new TreeNode(root_val);

        Integer in_inx = dic.get(root_val);
        pre_inx++;

        node.left = helper(start, in_inx - 1);
        node.right = helper(in_inx + 1, end);

        return node;
    }
}
