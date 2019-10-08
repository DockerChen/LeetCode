package Graph;

        import java.util.HashMap;
        import java.util.HashSet;
//图
public class Graph {
    public HashMap<Integer, Vertex> vertexs;
    public HashSet<Edge> edges;

    public Graph() {
        vertexs = new HashMap<Integer, Vertex>();
        edges = new HashSet<Edge>();
    }
}
