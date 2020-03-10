import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PrintBinaryTree {

    static class Tree {
        int value;
        Tree left;
        Tree right;

        Tree(int value) {
            this.value = value;
        }
    }

    public void printTree(Tree root) {
        if (root == null) {
            return;
        }
        Queue<Tree> queue = new LinkedList<>();
        Map<Tree, Integer> map = new HashMap();
        int index = 1;
        queue.add(root);
        map.put(root, index);
        System.out.print(1 + ":");
        while (!queue.isEmpty()) {
            Tree node = queue.poll();

            int cur = map.get(node);
            if (index != cur) {
                System.out.println();
                System.out.print(cur + ":");
                index = cur;
            }
            System.out.print(node.value + " ");
            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, map.get(node) + 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, map.get(node) + 1);
            }

        }

    }

    public void printTree_1(Tree root) {
        if (root == null) {
            return;
        }
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        int index=1;
        while (!queue.isEmpty()) {
            System.out.print(index+": ");
            index++;
            //获取当前层的节点数量
            int size = queue.size();
            while (size > 0) {
                size--;
                Tree node = queue.poll();
                System.out.print(node.value + " ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Tree root = new Tree(1);
        Tree left1 = new Tree(2);
        Tree right1 = new Tree(3);
        Tree left21 = new Tree(4);
        Tree right21 = new Tree(5);
        Tree left22 = new Tree(6);
        Tree right22 = new Tree(7);
        root.left = left1;
        root.right = right1;
        left1.left = left21;
        left1.right = right21;
        right1.left = left22;
        right1.right = right22;

        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
//            printBinaryTree.printTree(root);
        printBinaryTree.printTree_1(root);

    }
}

