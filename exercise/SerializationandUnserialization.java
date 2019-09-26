import javax.lang.model.element.QualifiedNameable;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class SerializationandUnserialization {

    //按先序遍历
    public static String serializationByPreOrder(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serializationByPreOrder(head.left);
        res += serializationByPreOrder(head.right);
        return res;
    }

    public static Node unSerializationByPreOrderString(String str) {
        String[] strings = str.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < strings.length; i++) {
            queue.add(strings[i]);

        }
        return unSerializationByPreOrder(queue);

    }

    public static Node unSerializationByPreOrder(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(str));
        head.left = unSerializationByPreOrder(queue);
        head.right = unSerializationByPreOrder(queue);
        return head;

    }

    //按层遍历
    public static String serializationByLevel(Node head) {
        if (head == null) {
            return "#_";
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        String res = head.value + "_";
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                queue.add(head.left);
                res+=head.left.value+"_";
            }else {
                res+="#_";
            }

            if(head.right!=null){
                queue.add(head.right);
                res+=head.right.value+"_";
            }else {
                res+="#_";
            }
        }

        return res;
    }

    public static Node unSerializationByLevel(String str){

        String[] strings=str.split("_");
        Queue<Node> queue=new LinkedList<>();
        int index=0;
        Node head=new Node(Integer.valueOf(strings[index++]));
        queue.add(head);

        Node node=null;

        while(!queue.isEmpty()){
            node=queue.poll();
            node.left=getNode(strings[index++]);
            //不要把null节点放进队列
            if(node.left!=null){
                queue.add(node.left);
            }
            node.right=getNode(strings[index++]);
            if(node.right!=null){
                queue.add(node.right);
            }
        }

        return head;

    }

    public static Node getNode(String str){
        if(str.equals("#")){
           return null;
        }else {
          return new Node(Integer.valueOf(str));
        }


    }


    public static void main(String[] args) {

    }


}
