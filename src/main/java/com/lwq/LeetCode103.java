package com.lwq;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class LeetCode103 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int level = 0;
        dfs(root, level, res);
        return res;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> res) {
        if (level >= res.size()) {
            //如果是第一次访问该层的节点，即该层的双端队列不存在。那么创建一个双端队列，并添加该节点到队列中。
            LinkedList<Integer> newLevel = new LinkedList<>();
            newLevel.add(node.val);
            res.add(newLevel);
        } else {
            //如果当前层的双端队列已存在，根据顺序，将当前节点插入队列头部或尾部。
            if (level % 2 == 0) {
                // 正序写入level层
                res.get(level).add(node.val);
            } else {
                // 倒序写入level层
                res.get(level).add(0, node.val);
            }
        }
        //为每个节点调用该递归方法。
        if (node.left != null) {
            dfs(node.left, level + 1, res);
        }
        if (node.right != null) {
            dfs(node.right, level + 1, res);
        }
    }
}
