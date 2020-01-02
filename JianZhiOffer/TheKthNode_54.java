public class TheKthNode_54 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    private int cnt = 0;
    private TreeNode res = null;

    TreeNode KthNode(TreeNode pRoot, int k) {
        InOrder(pRoot, k);

        return res;


    }

    public void InOrder(TreeNode pRoot, int k) {
        if (pRoot == null || cnt > k) {
            return;
        }
        InOrder(pRoot.left, k);
        cnt++;
        if (cnt == k) {
            res = pRoot;
        }

        InOrder(pRoot.right, k);
    }


}
