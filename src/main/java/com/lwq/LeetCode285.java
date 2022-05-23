package com.lwq;

/**
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * <p>
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/P5rCT8
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode285 {
    public static void main(String[] args) {

    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //如果当前节点有右子树，则返回右子树的最左节点
        if (p.right != null) {
            return leftMostChild(p.right);
        }
        //如果当前节点没有右子树，则返回第一个比当前节点大的节点
        TreeNode successor = null;
        TreeNode current = root;
        while (current != null) {
            if (current.val > p.val) {
                // 如果当前节点比p大，则往左边找
                successor = current;
                current = current.left;
            } else {
                // 如果当前节点比p大，则往右边找
                current = current.right;
            }
        }
        return successor;
    }

    private TreeNode leftMostChild(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
