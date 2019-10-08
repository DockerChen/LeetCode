package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void bfs(Vertex vertex) {
        Queue<Vertex> queue = new LinkedList<>();
        HashSet<Vertex> hashSet = new HashSet<>();
        queue.add(vertex);
        hashSet.add(vertex);
        while (!queue.isEmpty()) {
            Vertex vertex1 = queue.poll();
            System.out.println(vertex1.value);
            for (Vertex v : vertex1.nexts) {
                if (!hashSet.contains(v)) {
                    hashSet.add(v);
                    queue.add(v);
                    System.out.println(v.value);
                }
            }
        }

    }
}
