package com.lwq.codecatalog.Binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建二叉树
 */
public class ConstructBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();
        TreeNode root = constructBinaryTree.constructBinaryTree(arr);
        System.out.println(root);
    }
    /**
     * 根据数组构建二叉树
     *
     * @param arr 树的数组表示
     * @return 构建成功后树的根节点
     */
    public TreeNode constructBinaryTree(final int[] arr) {
        List<TreeNode> list = arr.length > 0 ? new ArrayList<TreeNode>() : null;
        TreeNode root = null;
        for (int i = 0; i < arr.length; i++) {
            TreeNode node = null;
            if (arr[i] != -1) {
                //-1代表该节点为空
                node = new TreeNode(arr[i]);
            }
            list.add(node);
            if (i == 0) {
                //根节点
                root = node;
            }
        }
        // 遍历一遍，根据规则左右孩子赋值就可以了
        // 注意这里 结束规则是 i * 2 + 2 < arr.length，避免空指针
        for (int i = 0; i * 2 + 2 < arr.length; i++) {
            TreeNode node = list.get(i);
            if (node != null) {
                node.left = list.get(i * 2 + 1);
                node.right = list.get(i * 2 + 2);
            }
        }
        return root;
    }
}
