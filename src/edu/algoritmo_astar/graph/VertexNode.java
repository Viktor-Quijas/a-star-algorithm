package edu.algoritmo_astar.graph;
import java.util.List;
import java.util.ArrayList;

public class VertexNode {
    private double h;
    private double g;

    public int id;
    public int m;
    public int n;
    public int type;
    public VertexNode parent;
    public List<EdgeNode> edges;

    private boolean amAClone;

    public VertexNode(int id){
        this.id = id;
        amAClone = false;
    }

    public VertexNode (VertexNode node){
        this.id = node.id;
        this.n = node.n;
        this.m = node.m;
        this.type = node.type;
        this.edges = node.edges;

        g = 0;
        h = 0;
        parent = null;

        amAClone = true;
    }

    public void setValueH(VertexNode goal){
        if (!amAClone)
            return;

        double dm = Math.abs(m - goal.m);
        double dn = Math.abs(n - goal.m);

        h = NodeConstants.SQUARE_GRID_LINE_WEIGHT * (dm + dn) + (NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT - 2 * NodeConstants.SQUARE_GRID_LINE_WEIGHT) * Math.min(dm,dn);
    }

    public void setValueG(VertexNode actualNode, EdgeNode typeEdge){
        if (!amAClone)
            return;

        g = actualNode.g + typeEdge.weight;
    }

    public double getValueF(){ return g + h; }

    public VertexNode(){}
}
