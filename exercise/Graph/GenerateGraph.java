package Graph;
//生成图
public class GenerateGraph {
    public static Graph generateGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = matrix[i][2];
            if (!graph.vertexs.containsKey(from)) {
                graph.vertexs.put(from, new Vertex(from));
            }
            if (!graph.vertexs.containsKey(to)) {
                graph.vertexs.put(to, new Vertex(to));
            }
            Vertex fromVertex = graph.vertexs.get(from);
            Vertex toVertex = graph.vertexs.get(to);
            Edge newEdge = new Edge(weight, fromVertex, toVertex);
            fromVertex.nexts.add(toVertex);
            fromVertex.out++;
            toVertex.in++;
            fromVertex.edges.add(newEdge);
            graph.edges.add(newEdge);
        }

        return graph;

    }
}
