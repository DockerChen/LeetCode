package Graph;
//边
public class Edge {
    public int weight;
    public Vertex from;
    public Vertex to;

    public Edge(int weight, Vertex from, Vertex to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
