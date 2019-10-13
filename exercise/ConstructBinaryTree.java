
public class ConstructBinaryTree {


    public static Node constructBinaryTreeByPreInOrder(int[] preOrder, int[] inOrder, int preOrder_start,
                                                       int inOrder_start, int length) {
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

    public static Node constructBinaryTreeByPosInOrder(int[] posOrder, int[] inOrder, int posOrder_end,
                                                       int inOrder_start, int length) {
        if (length == 0) {
            return null;
        }

        int rootInOrderIndex = 0;
        for (int i = inOrder_start; i < inOrder_start + length; i++) {
            if (posOrder[posOrder_end] == inOrder[i]) {
                rootInOrderIndex = i;
                break;
            }
        }
        int left_length = rootInOrderIndex - inOrder_start;
        int right_length = length - left_length - 1;


        Node root = new Node(posOrder[posOrder_end]);
        root.left = constructBinaryTreeByPosInOrder(posOrder, inOrder, posOrder_end - right_length - 1,
                inOrder_start, left_length);
        root.right = constructBinaryTreeByPosInOrder(posOrder, inOrder, posOrder_end - 1,
                rootInOrderIndex + 1, right_length);

        return root;
    }


    //testCase
    //            1
    //          /   \
    //         2     3
    //        / \
    //       4   5
    //pre->12453  in->42513   post->45231

    public static void main(String[] args) {
        //test
        int[] preOrder = {1, 2, 4, 5, 3};
        int[] inOrder = {4, 2, 5, 1, 3};
        int[] posOrder = {4, 5, 2, 3, 1};
        PreInPosTraverseTree preInPosTraverseTree = new PreInPosTraverseTree();
        //前序中序
        Node head = constructBinaryTreeByPreInOrder(preOrder, inOrder, 0,
                0, preOrder.length);
        System.out.println("By pre-in order construct:");
        System.out.println("pre order:");
        preInPosTraverseTree.preRecursionTraver(head);
        System.out.println();
        System.out.println("in order:");
        preInPosTraverseTree.inRecursionTraver(head);
        System.out.println();
        System.out.println("pos order:");
        preInPosTraverseTree.posRecursionTraver(head);
        System.out.println();

        //后续中序
        head = constructBinaryTreeByPosInOrder(posOrder, inOrder, posOrder.length - 1,
                0, posOrder.length);
        System.out.println("By pos-in order construct:");
        System.out.println("pre order:");
        preInPosTraverseTree.preRecursionTraver(head);
        System.out.println();
        System.out.println("in order:");
        preInPosTraverseTree.inRecursionTraver(head);
        System.out.println();
        System.out.println("pos order:");
        preInPosTraverseTree.posRecursionTraver(head);
        System.out.println();


    }
}
