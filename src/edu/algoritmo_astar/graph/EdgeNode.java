package edu.algoritmo_astar.graph;

public class EdgeNode {
    public double weight;
    public int state;
    public double g;
    public double h;
    public double f;
    public VertexNode pointingTo;
    public VertexNode originFrom;

    public EdgeNode(double weight, VertexNode pointingTo, VertexNode originFrom){
        this.weight = weight;
        this.pointingTo = pointingTo;
        this.originFrom = originFrom;

        h = 0;
        f = 0;
        g = 0;

        state = NodeConstants.WAITING;
    }

    public void setValueH(VertexNode goal){
        double dm = Math.abs(pointingTo.m - goal.m);
        double dn = Math.abs(pointingTo.n - goal.m);

        h = NodeConstants.SQUARE_GRID_LINE_WEIGHT * (dm + dn) + (NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT - 2 * NodeConstants.SQUARE_GRID_LINE_WEIGHT) * Math.min(dm,dn);
    }

    public void setValueG(EdgeNode parent){
        this.g = parent.g + weight;
    }

    public double getValueF(){ return g + f; }

    public EdgeNode() {}
}
