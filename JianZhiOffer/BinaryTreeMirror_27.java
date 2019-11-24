public class BinaryTreeMirror_27 {
    class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }


    public void mirrorRecursive(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        BinaryTreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirrorRecursive(root.left);
        mirrorRecursive(root.right);
    }

}
