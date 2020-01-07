import java.util.Stack;

public class IsSearchTree {
    //在递归中序遍历代码的基础上进行修改
    public static boolean isSearchTree(Node head) {
        String str = "" + inOrderTraver(head, new StringBuilder(""));

        String[] res = str.split(" ");
        int[] arr = new int[res.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(res[i]);
        }

        boolean flag = true;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static StringBuilder inOrderTraver(Node head, StringBuilder str) {

        if (head == null) {
            return null;
        }
        inOrderTraver(head.left, str);
        str.append(head.value + " ");
        inOrderTraver(head.right, str);

        return str;
    }


    //在非递归中序遍历代码的基础上进行修改
    public boolean isSearchTreeImprove(Node head) {
        if (head == null) {
            return false;
        }
        //保存前一个节点的值
        int pre = 0;
        int index = 0;
        Stack<Node> stack = new Stack<>();

        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {

                head = stack.pop();

                index++;
                //从第二个节点开始比较
                if (index > 1) {
                    if (pre > head.value) {
                        return false;
                    }
                }
                pre = head.value;

                head = head.right;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println(isSearchTree(head) ? "true" : "false");
//        System.out.println(Integer.toBinaryString(-5));

//        PreInPosTraverseTree preInPosTraverseTree=new PreInPosTraverseTree();
//        preInPosTraverseTree.inRecursionTraver(head);
    }
}
