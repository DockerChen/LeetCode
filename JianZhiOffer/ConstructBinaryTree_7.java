public class ConstructBinaryTree_7 {
    public static Node constructBinaryTreeByPreInOrder(int[] preOrder, int[] inOrder, int preOrder_start, int inOrder_start, int length) {
        if (length == 0) {
            return null;
        }

        int rootInOrderIndex = 0;
        for (int i = inOrder_start; i < inOrder_start + length; i++) {
            if (preOrder[preOrder_start] == inOrder[i]) {
                rootInOrderIndex = i;
                break;
            }
        }
        int left_length = rootInOrderIndex - inOrder_start;
        int right_length = length - left_length - 1;
        //根节点
        Node root = new Node(preOrder[preOrder_start]);
        //构建左子树
        root.left = constructBinaryTreeByPreInOrder(preOrder, inOrder, preOrder_start + 1,
                inOrder_start, left_length);
        //构建右子树
        root.right = constructBinaryTreeByPreInOrder(preOrder, inOrder, preOrder_start + left_length + 1,
                rootInOrderIndex + 1, right_length);
        return root;

    }

}
