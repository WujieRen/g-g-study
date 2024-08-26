package cn.rwj.study.java.algrithm.tree;

/**
 * @author rwj
 * @since 2024/8/22
 */
public class BinarySearchTree {

    private TreeNode root;

    public void insert(int value) {
        inst(root, value);
    }

    public void inst(TreeNode root, int value) {
        if(root == null) return;
        if(value > root.value) {
            if(root.right == null) {
                root.right = new TreeNode(value);
                return;
            }
            inst(root.right, value);
        }
        if(value <= root.value){
            if(root.left == null) {
                root.left = new TreeNode(value);
                return;
            }
            inst(root.left, value);
        }
    }

    public TreeNode search(int value) {
        return sh(root, value);
    }

    public TreeNode sh(TreeNode root, int value) {
        if(root == null) return null;
        if(root.value > value) return sh(root.left, value);
        if(root.value < value) return sh(root.right, value);
        return root;
    }

}
