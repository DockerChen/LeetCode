import java.util.LinkedList;
import java.util.Queue;

public class SerializationAndUnserializationBinaryTree_37 {
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
        }
    }

    public static String serialization(BinaryTree root) {
        if (root == null) {
            return "$,";
        }
        String res = root.value + ",";
        res += serialization(root.left);
        res += serialization(root.right);
        return res;
    }

    public static BinaryTree unSerialization(String str) {
        String[] strings = str.split(",");
        Queue<String> queue = new LinkedList();
        for (int i = 0; i < strings.length; i++) {
            queue.add(strings[i]);
        }
        return unSerializationByPreOrder(queue);

    }

    public static BinaryTree unSerializationByPreOrder(Queue<String> queue) {
        String str = queue.poll();
        if (str == "$") {
            return null;
        }
        BinaryTree head = new BinaryTree(Integer.valueOf(str));
        head.left = unSerializationByPreOrder(queue);
        head.right = unSerializationByPreOrder(queue);

        return head;

    }

}
