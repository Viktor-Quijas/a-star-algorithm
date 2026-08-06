package edu.algoritmo_astar.graph;
import edu.algoritmo_astar.map.MapConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreationSquareGridGraph {
    protected final int m;
    protected final int n;
    protected int[][] matrix;
    protected VertexNode[][] graph;

    public CreationSquareGridGraph(int m, int n, int[][] matrix){
        this.m = m;
        this.n = n;
        this.matrix = matrix;
    }

    private void createGraphNodes(){
        graph = new VertexNode[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                graph[i][j] = new VertexNode(i);

                nodeMapping(graph[i][j], matrix[i][j]);
                nodeAlignment(graph[i][j], i, j);
            }
        }




        IO.println("Grafo creado exitosamente!");
    }

    private void nodeMapping(VertexNode node, int matrixNodeType){
        if (node == null)
            return;

        if (matrixNodeType == MapConstants.BLANK)
            node.type = NodeConstants.BLANK;

        else if (matrixNodeType == MapConstants.OCCUPIED)
            node.type = NodeConstants.OCCUPIED;

        else if (matrixNodeType == MapConstants.START)
            node.type = NodeConstants.START;

        else if (matrixNodeType == MapConstants.GOAL)
            node.type = NodeConstants.GOAL;
    }

    private void nodeAlignment(VertexNode node, int x, int y){
        if (node == null)
            return;

        node.x = x;
        node.y = y;
    }

    private List<EdgeNode> getCornerEdge(VertexNode node, int dx, int dy){
        if (node == null)
            return Collections.emptyList();

        List<EdgeNode> edgeList = new ArrayList<>();
        EdgeNode edge;

        if (m > 1) {
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x + dx][node.y], node);
            edgeList.add(edge);
        }
        if (n > 1){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x][node.y + dy], node);
            edgeList.add(edge);
        }
        if (m > 1 && n > 1){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x + dx][node.y + dy], node);
            edgeList.add(edge);
        }

        return edgeList;
    }

    private List<EdgeNode> getBorderEdge(VertexNode node, int dx, int dy){
        if (node == null)
            return Collections.emptyList();

        List<EdgeNode> edgeList = new ArrayList<>();
        EdgeNode edge;

        if (dx == 0){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x - 1][node.y], node);
            edgeList.add(edge);
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x + 1][node.y], node);
            edgeList.add(edge);

            if (node.y + dy < n && node.y + dy >= 0){
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x][node.y + dy], node);
                edgeList.add(edge);

                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x - 1][node.y + dy], node);
                edgeList.add(edge);
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x + 1][node.y + dy], node);
                edgeList.add(edge);
            }
        } else if (dy == 0){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x][node.y - 1], node);
            edgeList.add(edge);
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x][node.y + 1], node);
            edgeList.add(edge);

            if (node.x + dx < m && node.x + dx >= 0){
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x + dx][node.y], node);
                edgeList.add(edge);

                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x + dx][node.y - 1], node);
                edgeList.add(edge);
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x + dx][node.y + 1], node);
                edgeList.add(edge);
            }
        }

        return edgeList;
    }

    private List<EdgeNode> getCenterEdge(VertexNode node){
        if (node == null)
            return Collections.emptyList();

        List<EdgeNode> edgeList = new ArrayList<>();

        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x + 1][node.y], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x - 1][node.y], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x][node.y + 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.x][node.y - 1], node));

        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x + 1][node.y - 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x + 1][node.y + 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x - 1][node.y - 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.x - 1][node.y + 1], node));

        return edgeList;
    }

    private void edgeAssignation(VertexNode node){
        if (node == null)
            return;

        //UP_LEFT_CORNER
        if (node.x == 0 && node.y == 0)
            node.edges = getCornerEdge(node, 1, 1);
        //UP_RIGHT_CORNER
        else if (node.x == 0 && node.y == n - 1)
            node.edges = getCornerEdge(node, -1, 1);
        //DOWN_LEFT_CORNER
        else if (node.x == m - 1 && node.y == 0)
            node.edges = getCornerEdge(node, 1, -1);
        //DOWN_RIGHT_CORNER
        else if (node.x == m - 1 && node.y == n - 1)
            node.edges = getCornerEdge(node, -1, -1);
        //NORTH_BORDER
        else if (node.x == 0)
            node.edges = getBorderEdge(node, 0, -1);
        //SOUTH_BORDER
        else if (node.x == m - 1)
            node.edges = getBorderEdge(node, 0, 1);
        //WEST_BORDER
        else if (node.y == 0)
            node.edges = getBorderEdge(node, 1, 0);
        //EAST_BORDER
        else if (node.y == n - 1)
            node.edges = getBorderEdge(node, -1, 0);
        //CENTER
        else
            node.edges = getCenterEdge(node);
    }

    public void squareGridGraphCreation(){
        if (m <= 0 || n <= 0)
            return;

        graph = null;

        createGraphNodes();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                edgeAssignation(graph[i][j]);
            }
        }
    }

    protected VertexNode[][] getGraph(){ return graph; }
}
