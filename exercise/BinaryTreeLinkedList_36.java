public class BinaryTreeLinkedList_36 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    //pLast 用于记录当前链表的末尾节点。
    private TreeNode pLast = null;

    public TreeNode Convert(TreeNode root) {
        if (root == null)
            return null;

        // 如果左子树为空，那么根节点root为双向链表的头节点
        TreeNode head = Convert(root.left);
        if (head == null)
            head = root;

        // 连接当前节点root和当前链表的尾节点pLast
        root.left = pLast;
        if (pLast != null)
            pLast.right = root;
        pLast = root;

        Convert(root.right);

        return head;
    }
}
