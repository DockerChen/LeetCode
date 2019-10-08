package Graph;

import java.util.*;

public class TopologySort {
    public static List<Vertex> topologySort(Graph graph) {
        HashMap<Vertex, Integer> inMap = new HashMap<>();
        Queue<Vertex> zeroQueue = new LinkedList<>();
        List<Vertex> list = new ArrayList<>();
        for (Vertex vertex : graph.vertexs.values()) {
            inMap.put(vertex, vertex.in);
            if (vertex.in == 0) {
                zeroQueue.add(vertex);
            }
        }

        while (!zeroQueue.isEmpty()) {
            Vertex vertex = zeroQueue.poll();
            list.add(vertex);
            for (Vertex v : vertex.nexts) {
                inMap.put(v, inMap.get(v) - 1);
                if (inMap.get(v) == 0) {
                    zeroQueue.add(v);
                }

            }
        }
        return list;

    }
}
