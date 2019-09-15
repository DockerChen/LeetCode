
public class ConstructBinaryTree {

    public static Node constructBinaryTree(int[] preOrder, int[] inOrder, int preOrder_start, int inOrder_start, int length) {
        if (length==0){
            return null;
        }

        int rootInOrderIndex=0;
        for (int i = inOrder_start; i < inOrder_start+length; i++) {
            if(preOrder[preOrder_start]==inOrder[i]){
                rootInOrderIndex=i;
                break;
            }
        }
        int left_length=rootInOrderIndex-inOrder_start;
        int right_length=length-left_length-1;

        Node head=new Node(preOrder[preOrder_start]);
        head.left=constructBinaryTree(preOrder,inOrder,preOrder_start+1,inOrder_start,left_length);
        head.right=constructBinaryTree(preOrder,inOrder,preOrder_start+left_length+1,rootInOrderIndex+1,right_length);
        return head;

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
        Node head = constructBinaryTree(preOrder, inOrder, 0, 0, preOrder.length);

        PreInPosTraverseTree preInPosTraverseTree=new PreInPosTraverseTree();

        System.out.println("pre order：");
        preInPosTraverseTree.preRecursionTraver(head);
        System.out.println();
        System.out.println("in order：");
        preInPosTraverseTree.inRecursionTraver(head);
        System.out.println();
        System.out.println("pos order：");
        preInPosTraverseTree.posRecursionTraver(head);
        System.out.println();


    }
}
