public class FindNextNode_8 {
    public static TreeNode findNextNode(TreeNode treeNode) {
        //当前节点有右子树，下一个节点是右子树中最左的节点
        if (treeNode.right != null) {
            TreeNode cur = treeNode.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        } else {
            //无右子树
            TreeNode par = treeNode.parent;
            //父节点的左孩子是当前节点，下一个节点是父节点
            if (par.left == treeNode) {
                return par;
            } else {
                //遍历该节点的父节点，直到父节点的左孩子是当前节点，下一个节点是父节点
                while (par.left != treeNode) {
                    par = par.parent;
                    treeNode = treeNode.parent;
                }
                return par;
            }

        }

    }
}
