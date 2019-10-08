package Graph;

import java.util.*;

public class Prim {

    public static List<Edge> edgeList(Graph graph) {
        List<Edge> edgeList = new ArrayList<>();
        HashSet<Vertex> vertexHashSet = new HashSet<>();
        //小根堆
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (Vertex vertex : graph.vertexs.values()) {
            //获得第一个遍历的顶点
            if (!vertexHashSet.contains(vertex)) {
                vertexHashSet.add(vertex);
                //往小根堆中添加与该顶点相关联的边（按边权值排序）
                for (Edge edge : vertex.edges) {
                    edgePriorityQueue.add(edge);
                }

                while (!edgePriorityQueue.isEmpty()) {
                    Edge edge = edgePriorityQueue.poll();
                    //获得当前最小边的下一个顶点
                    Vertex toVertex = edge.to;
                    if (!vertexHashSet.contains(toVertex)) {
                        edgeList.add(edge);
                        //往小根堆中添加与该顶点相关联的边（按边权值排序）
                        for (Edge edge1 : toVertex.edges) {
                            edgePriorityQueue.add(edge1);
                        }
                    }

                }

            }
        }
        return edgeList;

    }
}
