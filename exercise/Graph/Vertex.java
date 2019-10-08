package Graph;

import java.util.ArrayList;
//顶点
public class Vertex {
    public int value;
    public int in;
    public int out;
    public ArrayList<Vertex> nexts;
    public ArrayList<Edge> edges;

    public Vertex(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<Vertex>();
        this.edges = new ArrayList<Edge>();

    }
}
