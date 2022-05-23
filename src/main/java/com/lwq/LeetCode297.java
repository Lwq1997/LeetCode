package com.lwq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode297 {
    public static void main(String[] args) {
        //创建一个二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        LeetCode297 leetCode297 = new LeetCode297();
        String s = leetCode297.serialize(root);
        System.out.println(s);
        TreeNode treeNode = leetCode297.deserialize(s);
        System.out.println(treeNode);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "#_";
        }
        String res = root.val+"_";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split("_");
        Queue<String> queue = new LinkedList<>();
        for (String s1 : s) {
            queue.add(s1);
        }
        return rePreOrder(queue);
    }

    private TreeNode rePreOrder(Queue<String> queue) {
        String poll = queue.poll();
        if(poll.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(poll));
        root.left = rePreOrder(queue);
        root.right = rePreOrder(queue);
        return root;
    }
}
