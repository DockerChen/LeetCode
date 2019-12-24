import java.util.ArrayList;

public class SumToPath_34 {
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
        }
    }


    public static void sumToPath(BinaryTree binaryTree, int target) {

        process(binaryTree, target, "");

    }

    public static void process(BinaryTree binaryTree, int target, String str) {
        if (binaryTree == null) {
            return;
        }
        str = str +binaryTree.value+"-";
        target = target - binaryTree.value;

        if (target == 0 && binaryTree.left == null && binaryTree.right == null) {
            System.out.println(str);

        }
        process(binaryTree.left, target, str);
        process(binaryTree.right, target, str);

    }

/*    private static ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> list = new ArrayList<Integer>();

    public static ArrayList<ArrayList<Integer>> FindPath(BinaryTree root, int target) {
        if (root == null) return listAll;
        list.add(root.value);
        target -= root.value;
        if (target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<Integer>(list));
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }*/

    public static void main(String[] args) {

        BinaryTree root = new BinaryTree(8);
        BinaryTree left1 = new BinaryTree(6);
        BinaryTree right1 = new BinaryTree(10);
        BinaryTree left21 = new BinaryTree(5);
        BinaryTree right21 = new BinaryTree(7);
        BinaryTree left22 = new BinaryTree(9);
        BinaryTree right22 = new BinaryTree(1);

        root.left = left1;
        root.right = right1;
        left1.left = left21;
        left1.right = right21;
        right1.left = left22;
        right1.right = right22;

        sumToPath(root, 19);

  /*      ArrayList<ArrayList<Integer>> res = FindPath(root, 19);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }*/

    }
}
