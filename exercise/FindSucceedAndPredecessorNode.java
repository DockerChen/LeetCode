import javafx.scene.Parent;

import javax.xml.stream.events.NotationDeclaration;
import java.lang.reflect.Parameter;

public class FindSucceedAndPredecessorNode {
    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }

    }


    public static Node findSucceedNode(Node node) {
        if(node==null){
            return node;
        }
        if(node.right!=null){
            node=node.right;
            while (node.left!=null){
                node=node.left;
            }
            return node;
        }else {
            Node cur=node;
            node=node.parent;
            while(node!=null&&cur!=node.left){
                cur=node;
                node=node.parent;
            }
            return node;
        }

    }

    public static Node findPredecessorNode(Node node){
        if(node==null){
            return node;
        }
        if(node.left!=null){
            node=node.left;
            while (node.right!=null){
                node=node.right;
            }
            return node;
        }else {
            Node current=node;
            node=node.parent;
            while(node!=null&&node.right!=current){
                current=node;
                node=node.parent;
            }
            return node;

        }

    }


}
