import com.sun.xml.internal.ws.server.ServerRtException;

import java.util.*;
import java.util.LinkedList;

public class PrintBinaryTree_32 {
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void printBinaryTree(BinaryTree root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTree binaryTree = queue.poll();

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

    public static void printBinaryTree_2(BinaryTree root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTree> queue = new LinkedList<>();
        int index = 0;
        Map<BinaryTree, Integer> map = new HashMap();
        map.put(root, index);
        queue.add(root);

        int pre = index;
        while (!queue.isEmpty()) {
            BinaryTree binaryTree = queue.poll();
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

    public static void printBinaryTree_3(BinaryTree root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTree> stack1 = new Stack<>();
        Stack<BinaryTree> stack2 = new Stack<>();
        //记录节点对应的层数
        Map<BinaryTree, Integer> map = new LinkedHashMap<>();
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

                    BinaryTree node = stack1.pop();
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
                    BinaryTree node = stack2.pop();
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
        BinaryTree root = new BinaryTree(8);
        BinaryTree left1 = new BinaryTree(6);
        BinaryTree right1 = new BinaryTree(10);
        BinaryTree left21 = new BinaryTree(5);
        BinaryTree right21 = new BinaryTree(7);
        BinaryTree left22 = new BinaryTree(9);
        BinaryTree right22 = new BinaryTree(11);

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
