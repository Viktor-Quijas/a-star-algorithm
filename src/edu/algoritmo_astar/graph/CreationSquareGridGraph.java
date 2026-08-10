package edu.algoritmo_astar.graph;
import edu.algoritmo_astar.map.CreationMap;
import edu.algoritmo_astar.map.MapConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreationSquareGridGraph {
    private final int m;
    private final int n;
    private final int[][] matrix;
    private VertexNode[][] graph;

    private void createGraphNodes(){
        graph = new VertexNode[m][n];

        int setID = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                graph[i][j] = new VertexNode(setID);

                nodeMapping(graph[i][j], matrix[i][j]);
                nodeAlignment(graph[i][j], i, j);
                setID++;
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

        node.m = x;
        node.n = y;
    }

    private List<EdgeNode> getCornerEdge(VertexNode node, int dx, int dy){
        if (node == null)
            return Collections.emptyList();

        List<EdgeNode> edgeList = new ArrayList<>();
        EdgeNode edge;

        if (n > 1) {
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m][node.n + dy], node);
            edgeList.add(edge);
        }
        if (m > 1){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m + dx][node.n], node);
            edgeList.add(edge);
        }
        if (m > 1 && n > 1){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m + dx][node.n + dy], node);
            edgeList.add(edge);
        }

        return edgeList;
    }

    private List<EdgeNode> getBorderEdge(VertexNode node, int alignment, int direction){
        if (node == null)
            return Collections.emptyList();

        List<EdgeNode> edgeList = new ArrayList<>();
        EdgeNode edge;

        if (alignment == NodeConstants.ALIGNMENT_HORIZONTAL){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m][node.n - 1], node);
            edgeList.add(edge);
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m][node.n + 1], node);
            edgeList.add(edge);

            if (node.m + direction < m && node.m + direction >= 0){
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m + direction][node.n], node);
                edgeList.add(edge);

                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m + direction][node.n + 1], node);
                edgeList.add(edge);
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m + direction][node.n - 1], node);
                edgeList.add(edge);
            }
        } else if (alignment == NodeConstants.ALIGNMENT_VERTICAL){
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m - 1][node.n], node);
            edgeList.add(edge);
            edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m + 1][node.n], node);
            edgeList.add(edge);

            if (node.n + direction < n && node.n + direction >= 0){
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m][node.n + direction], node);
                edgeList.add(edge);

                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m - 1][node.n + direction], node);
                edgeList.add(edge);
                edge = new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m + 1][node.n + direction], node);
                edgeList.add(edge);
            }
        }

        return edgeList;
    }

    private List<EdgeNode> getCenterEdge(VertexNode node){
        if (node == null)
            return Collections.emptyList();

        List<EdgeNode> edgeList = new ArrayList<>();

        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m + 1][node.n], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m - 1][node.n], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m][node.n + 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT, graph[node.m][node.n - 1], node));

        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m + 1][node.n - 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m + 1][node.n + 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m - 1][node.n - 1], node));
        edgeList.add(new EdgeNode(NodeConstants.SQUARE_GRID_DIAGONAL_WEIGHT, graph[node.m - 1][node.n + 1], node));

        return edgeList;
    }

    private void edgeAssignation(VertexNode node){
        if (node == null)
            return;

        if (node.type == NodeConstants.OCCUPIED) {
            node.edges = Collections.emptyList();
            return;
        }

        //UP_LEFT_CORNER
        if (node.m == 0 && node.n == 0)
            node.edges = getCornerEdge(node, 1, 1);
        //UP_RIGHT_CORNER
        else if (node.m == 0 && node.n == n - 1)
            node.edges = getCornerEdge(node, 1, -1);
        //DOWN_LEFT_CORNER
        else if (node.m == m - 1 && node.n == 0)
            node.edges = getCornerEdge(node, -1, 1);
        //DOWN_RIGHT_CORNER
        else if (node.m == m - 1 && node.n == n - 1)
            node.edges = getCornerEdge(node, -1, -1);
        //NORTH_BORDER
        else if (node.m == 0)
            node.edges = getBorderEdge(node, NodeConstants.ALIGNMENT_HORIZONTAL, 1);
        //SOUTH_BORDER
        else if (node.m == m - 1)
            node.edges = getBorderEdge(node, NodeConstants.ALIGNMENT_HORIZONTAL, -1);
        //WEST_BORDER
        else if (node.n == 0)
            node.edges = getBorderEdge(node, NodeConstants.ALIGNMENT_VERTICAL, 1);
        //EAST_BORDER
        else if (node.n == n - 1)
            node.edges = getBorderEdge(node, NodeConstants.ALIGNMENT_VERTICAL, -1);
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

    public boolean edgesAreCorrect(){
        if (graph == null)
            return false;

        boolean isCorrect = true;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (graph[i][j].edges.isEmpty()) {
                    IO.println("Vértice: " + i + " " + j + " sin aristas!");
                    isCorrect = false;
                }
            }
        }

        return isCorrect;
    }

    public VertexNode getNode (int i, int j){
        if (graph == null) {
            IO.println("Ningún grafo ha sido creado!");
            return null;
        }

        if (i < 0 || j < 0 || i >= m || j >= n)
            return null;

        return graph[i][j];
    }

    public VertexNode[][] getGraph(){ return graph; }

    public CreationSquareGridGraph(CreationMap map){
        this.m = map.getM();
        this.n = map.getN();
        this.matrix = map.getMatrix();

        squareGridGraphCreation();
    }
}
