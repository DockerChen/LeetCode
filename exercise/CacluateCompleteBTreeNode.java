import java.util.Map;

public class CacluateCompleteBTreeNode {
    public static int getNodeNumbers(Node head) {
        if (head == null) {
            return 0;
        }
        int left = getNodeNumbers(head.left);
        int right = getNodeNumbers(head.right);
        return left + right + 1;
    }

    public static int getNodeNumbersImprove(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head, 1, getMaxHeight(head, 1));

    }

    public static int process(Node head, int curHeight, int maxHeight) {
        if(curHeight==maxHeight){
            return 1;
        }
        if(getMaxHeight(head.right,curHeight+1)==maxHeight){
            return (int) (Math.pow(2,maxHeight-curHeight)
                    + process(head.right,curHeight+1,maxHeight));
        }else{
            return (int)(Math.pow(2,maxHeight-curHeight-1)
                    +process(head.left,curHeight+1,maxHeight));
        }

    }

    public static int getMaxHeight(Node head, int curHeight) {
        while (head != null) {
            curHeight++;
            head = head.left;
        }
        return curHeight - 1;
    }

}
