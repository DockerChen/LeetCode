public class IsBalancedBinaryTree {
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        //求左子树高度
        int left = getHeight(node.left);
        if (left == -1)
            return -1;
        //求右子树高度
        int right = getHeight(node.right);
        if (right == -1)
            return -1;
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;

    }

    boolean isBalancedTree(Node head) {
        return getHeight(head) != -1;

    }

}
