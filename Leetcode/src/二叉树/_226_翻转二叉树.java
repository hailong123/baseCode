package 二叉树;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class _226_翻转二叉树 {

    public TreeNode invertTree(TreeNode root) {

        if (root == null) return null;

        TreeNode tmp = root;

        if (root.left != null) {
            root.left = tmp.right;
        }

        if (root.right != null) {
            root.right = tmp.left;
        }

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

}
