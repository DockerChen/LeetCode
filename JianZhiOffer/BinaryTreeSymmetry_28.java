public class BinaryTreeSymmetry_28 {
    class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public boolean isSymmetry(BinaryTreeNode root) {

        return process(root, root);
    }

    public boolean process(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.value != root2.value) {
            return false;
        }

        return process(root1.left, root2.right) && process(root1.right, root2.left);

    }

}
