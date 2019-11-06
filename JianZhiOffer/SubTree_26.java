public class SubTree_26 {
    static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(int value) {
            this.value = value;
        }
    }
    //遍历所有根节点值相同的子树
    public static boolean hasSubTree(BinaryTreeNode binaryTreeNode1, BinaryTreeNode binaryTreeNode2) {
        boolean result = false;
        if (binaryTreeNode1 != null && binaryTreeNode2 != null) {
            if (binaryTreeNode1.value == binaryTreeNode2.value) {
                result = judge(binaryTreeNode1, binaryTreeNode2);
            }
            if (!result) {
                result = hasSubTree(binaryTreeNode1.left, binaryTreeNode2);
            }

            if (!result) {
                result = hasSubTree(binaryTreeNode1.right, binaryTreeNode2);
            }

        }
        return result;

    }
    //判断根节点相同的子树是否完全一样
    public static boolean judge(BinaryTreeNode binaryTreeNode1, BinaryTreeNode binaryTreeNode2) {
        if (binaryTreeNode2 == null) {
            return true;
        }
        if (binaryTreeNode1 == null) {
            return false;
        }

        if (binaryTreeNode1.value != binaryTreeNode2.value) {
            return false;
        } else {
            return judge(binaryTreeNode1.left, binaryTreeNode2.left) && judge(binaryTreeNode1.right, binaryTreeNode2.right);
        }

    }

}
