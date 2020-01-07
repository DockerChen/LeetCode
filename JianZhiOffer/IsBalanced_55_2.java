public class IsBalanced_55_2 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return getHeight(root) != -1;

    }
    /*
    * -1：表示非平衡二叉树
    * 平衡二叉树左右子树高度之差不超过1，
    * 因此，可以递归遍历左右子树的高度，只要左右子树高度之差大于1，就立即返回-1
    * */
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }

        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;

    }


}
