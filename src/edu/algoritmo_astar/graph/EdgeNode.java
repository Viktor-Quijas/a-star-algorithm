package edu.algoritmo_astar.graph;

public class EdgeNode {
    public int weight;
    public int state;
    public int g;
    public int h;
    public int f;
    public VertexNode pointingTo;
    public VertexNode originFrom;

    public EdgeNode(int weight, VertexNode pointingTo, VertexNode originFrom){
        this.weight = weight;
        this.pointingTo = pointingTo;
        this.originFrom = originFrom;

        state = NodeConstants.WAITING;
    }

    public void setValueOfh(){

    }

    public void setValueOfg(){

    }

    public EdgeNode() {}
}
