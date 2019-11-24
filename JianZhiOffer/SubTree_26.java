public class SubTree_26 {
    static class BinaryTreeNode {
        double value;
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

        if (!Equals(binaryTreeNode1.value, binaryTreeNode2.value)) {
            return false;
        } else {
            return judge(binaryTreeNode1.left, binaryTreeNode2.left) && judge(binaryTreeNode1.right, binaryTreeNode2.right);
        }

    }
    //计算机表示小数（float、double）存在误差，不能直接用等号判断两个小数是否相等。如果两个小数的差的绝对值很小，
    // 小于0.0000001，则认为相等
    public static boolean Equals(double a, double b) {
        if (Math.abs(a - b) < 0.0000001) {
            return true;
        } else {
            return false;
        }
    }

}
