package com.lwq;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode236 {


    public static TreeNode res;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return res;
    }

    /**
     * 判断p和q是否在root的左右子树中
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return false;
        }
        //left_son说明左子树均包含 p 节点或 q 节点
        //right_son说明右子树均包含 p 节点或 q 节点
        boolean left_son = dfs(root.left,p,q);
        boolean right_son = dfs(root.right,p,q);

        // 如果当前节点的左右子树各包含p，q中的某一个，当前节点就是最近的祖先
        // 如果当前节点是p，切左右子树任意一个包含q，当前节点就是最近的祖先
        // 如果当前节点是q，切左右子树任意一个包含p，当前节点就是最近的祖先
        if((left_son && right_son) || ((root.val == p.val || root.val == q.val) && (left_son || right_son))){
            res = root;
        }

        // 只要当前节点的左子树中包含过p，q的任意一个，就是true
        // 只要当前节点的右子树中包含过p，q的任意一个，就是true
        // 只要当前节点为p，q的任意一个，就是true
        return left_son || right_son || (root.val == p.val || root.val == q.val);
    }
}
