import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnionFind {
    //自定义Node类型
    public static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("UnionFindSet{");
            sb.append("fatherMap=").append(fatherMap);
            sb.append(", sizeMap=").append(sizeMap);
            sb.append('}');
            return sb.toString();
        }

        public void makeSets(List<Node> nodes) {
            if (nodes == null) {
                return;
            }
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                if (aSize <= bSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSize + bSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSize + bSize);
                }

            }

        }

    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        UnionFindSet unionFindSet = new UnionFindSet();
        //test isSameSet()
        System.out.println(unionFindSet.isSameSet(node1, node2));

        List<Node> nodes = new ArrayList<>();

        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);

        unionFindSet.makeSets(nodes);

        //test union()
        unionFindSet.union(node1, node2);
        unionFindSet.union(node2, node3);
        unionFindSet.union(node3, node4);
        System.out.println(unionFindSet.isSameSet(node1, node4));

        //test findHead()
        System.out.println(unionFindSet.findHead(node4));

        System.out.println(unionFindSet);

    }

}
