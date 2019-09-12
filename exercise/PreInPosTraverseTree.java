import javax.swing.plaf.basic.BasicButtonUI;
import java.util.Stack;

public class PreInPosTraverseTree {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public void preRecursionTraver(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preRecursionTraver(head.left);
        preRecursionTraver(head.right);
    }

    public void inRecursionTraver(Node head) {
        if (head == null) {
            return;
        }
        inRecursionTraver(head.left);
        System.out.print(head.value + " ");
        inRecursionTraver(head.right);
    }

    public void posRecursionTraver(Node head) {
        if (head == null) {
            return;
        }
        posRecursionTraver(head.left);
        posRecursionTraver(head.right);
        System.out.print(head.value + " ");
    }

    public void preNoRecursionTraver(Node head) {
        System.out.println("pre order：");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }


    }

    public void inNoRecursionTraver(Node head) {
        System.out.println("in order：");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();

        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }

    }

    public void posNoRecursionTraver(Node head) {
        System.out.println("pos order：");
        if (head == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            head = stack1.pop();
            stack2.push(head);
            if (head.left != null) {
                stack1.push(head.left);
            }
            if (head.right != null) {
                stack1.push(head.right);
            }
        }

        while (!stack2.empty()) {

            System.out.print(stack2.pop().value + " ");
        }


    }


}
