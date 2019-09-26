import java.util.LinkedList;
import java.util.Queue;

public class IsCompleteBinaryTree {

    public static boolean isCompleteBinaryTree(Node head) {

        Queue<Node> queue = new LinkedList<>();
        boolean leaf = false;
        queue.add(head);

        Node left = null;
        Node right = null;
        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;
            if (left == null && right != null) {
                return false;
            }
            if (leaf && (left != null || right != null)) {
                return false;
            }
            if (left == null || right == null) {
                leaf = true;
            }

            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }

        return true;
    }
}
