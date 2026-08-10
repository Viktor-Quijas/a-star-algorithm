package edu.algoritmo_astar.graph;

public class EdgeNode {
    public double weight;
    public int state;
    public VertexNode pointingTo;
    public VertexNode originFrom;

    public EdgeNode(double weight, VertexNode pointingTo, VertexNode originFrom){
        this.weight = weight;
        this.pointingTo = pointingTo;
        this.originFrom = originFrom;

        state = NodeConstants.WAITING;
    }

    public EdgeNode() {}
}
