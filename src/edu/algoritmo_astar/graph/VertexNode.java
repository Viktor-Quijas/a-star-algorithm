package edu.algoritmo_astar.graph;
import java.util.List;
import java.util.ArrayList;

public class VertexNode {
    public int id;
    public int m;
    public int n;
    public int type;
    public List<EdgeNode> edges;

    public VertexNode(int id){
        this.id = id;
    }

    public VertexNode(){}
}
