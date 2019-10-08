package Graph;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public static void dfs(Vertex vertex) {
        Stack<Vertex> stack = new Stack<>();
        HashSet<Vertex> hashSet = new HashSet<>();
        stack.push(vertex);
        hashSet.add(vertex);
        while (!stack.isEmpty()) {
            Vertex vertex1 = stack.pop();
            System.out.println(vertex1.value);
            for (Vertex v : vertex1.nexts) {
                if (!hashSet.contains(v)) {
                    hashSet.add(v);
                    stack.add(v);
                    System.out.println(v.value);
                    break;
                }
            }
        }

    }
}
