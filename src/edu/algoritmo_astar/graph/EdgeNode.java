package edu.algoritmo_astar.graph;

public class EdgeNode {
    public int lineWeight;
    public double diagonalWeight;
    public int state;
    public int g;
    public int h;
    public int f;
    public VertexNode pointingTo;
    public VertexNode originFrom;

    public EdgeNode(int weight, VertexNode pointingTo, VertexNode originFrom){
        this.lineWeight = weight;
        this.pointingTo = pointingTo;
        this.originFrom = originFrom;

        state = NodeConstants.WAITING;
    }

    public EdgeNode(double weight, VertexNode pointingTo, VertexNode originFrom){
        this.diagonalWeight = weight;
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
