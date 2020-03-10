import java.util.*;
import java.util.LinkedList;

public class PrintBinaryTree_32 {
    public static class Tree {
        int value;
        Tree left;
        Tree right;

        Tree(int value) {
            this.value = value;
        }
    }

    public static void printBinaryTree(Tree root) {
        if (root == null) {
            return;
        }

        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Tree binaryTree = queue.poll();

            if (binaryTree.left != null) {
                queue.add(binaryTree.left);
            }
            if (binaryTree.right != null) {
                queue.add(binaryTree.right);
            }
            if (!queue.isEmpty()) {
                System.out.print(binaryTree.value + ",");
            } else {
                System.out.print(binaryTree.value);

            }

        }

    }

    public static void printBinaryTree_2(Tree root) {
        if (root == null) {
            return;
        }

        Queue<Tree> queue = new LinkedList<>();
        int index = 0;
        Map<Tree, Integer> map = new HashMap();
        map.put(root, index);
        queue.add(root);

        int pre = index;
        while (!queue.isEmpty()) {
            Tree binaryTree = queue.poll();
            index = map.get(binaryTree);
            index++;
            if (binaryTree.left != null) {
                map.put(binaryTree.left, index);
                queue.add(binaryTree.left);
            }
            if (binaryTree.right != null) {
                map.put(binaryTree.right, index);
                queue.add(binaryTree.right);
            }

            if (pre != map.get(binaryTree)) {
                System.out.println();
                pre = map.get(binaryTree);
                System.out.print(binaryTree.value);

            } else {
                if (map.get(binaryTree) == 0) {
                    System.out.print(binaryTree.value);
                } else {
                    System.out.print(" " + binaryTree.value);
                }
            }

        }
    }

    public static void printBinaryTree_3(Tree root) {
        if (root == null) {
            return;
        }

        Stack<Tree> stack1 = new Stack<>();
        Stack<Tree> stack2 = new Stack<>();
        //记录节点对应的层数
        Map<Tree, Integer> map = new LinkedHashMap<>();
        int index = 0;
        //根节点
        map.put(root, index);
        stack1.push(root);
        int pre = index;
        //标记变量
        boolean flag = true;

        /*每次处理一层，使用两个栈来存储节点*/
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (flag) {
                while (!stack1.isEmpty()) {

                    Tree node = stack1.pop();
                    index = map.get(node);
                    if (pre != index) {
                        System.out.println();
                        pre = index;
                    }
                    System.out.print(node.value + " ");
                    index++;

                    if (node.left != null) {
                        stack2.push(node.left);
                        map.put(node.left, index);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                        map.put(node.right, index);
                    }
                }
            } else {
                while (!stack2.isEmpty()) {
                    Tree node = stack2.pop();
                    index = map.get(node);
                    if (pre != index) {
                        System.out.println();
                        pre = index;
                    }
                    System.out.print(node.value + " ");
                    index++;

                    if (node.right != null) {
                        stack1.push(node.right);
                        map.put(node.right, index);
                    }
                    if (node.left != null) {
                        stack1.push(node.left);
                        map.put(node.left, index);
                    }
                }
            }
            flag = !flag;
        }

    }

    public static void main(String[] args) {
        Tree root = new Tree(8);
        Tree left1 = new Tree(6);
        Tree right1 = new Tree(10);
        Tree left21 = new Tree(5);
        Tree right21 = new Tree(7);
        Tree left22 = new Tree(9);
        Tree right22 = new Tree(11);

        root.left = left1;
        root.right = right1;
        left1.left = left21;
        left1.right = right21;
        right1.left = left22;
        right1.right = right22;

        System.out.println();
        System.out.println("==========");
        printBinaryTree(null);
        System.out.println();
        System.out.println("==========");
        printBinaryTree(root);
        System.out.println();
        System.out.println("==========");
        printBinaryTree_2(null);
        System.out.println();
        System.out.println("==========");
        printBinaryTree_2(root);
        System.out.println();
        System.out.println("==========");
        printBinaryTree_3(null);
        System.out.println();
        System.out.println("==========");
        printBinaryTree_3(root);

    }

}
