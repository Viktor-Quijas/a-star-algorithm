package edu.algoritmo_astar.graph;
import edu.algoritmo_astar.map.MapConstants;
import java.util.ArrayList;
import java.util.List;

public class CreationGraph {
    protected final int m;
    protected final int n;
    protected int[][] matrix;
    protected VertexNode[][] graph;

    public CreationGraph(int m, int n, int[][] matrix){
        this.m = m;
        this.n = n;
        this.matrix = matrix;
    }

    private void newSquareGridGraph(){
        graph = new VertexNode[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                graph[i][j] = new VertexNode(i);
            }
        }

        squareGridGraphMapping();
        squareGridGraphAlignment();

        IO.println("Grafo creado exitosamente!");
    }

    private void squareGridGraphMapping(){
        if (graph == null)
            return;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == MapConstants.BLANK)
                    graph[i][j].type = NodeConstants.BLANK;

                else if (matrix[i][j] == MapConstants.OCCUPIED)
                    graph[i][j].type = NodeConstants.OCCUPIED;

                else if (matrix[i][j] == MapConstants.START)
                    graph[i][j].type = NodeConstants.START;

                else if (matrix[i][j] == MapConstants.GOAL)
                    graph[i][j].type = NodeConstants.GOAL;
            }
        }
    }

    private void squareGridGraphAlignment(){
        if (graph == null)
            return;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                graph[i][j].x = i + 1;
                graph[i][j].y = j + 1;
            }
        }
    }

    private List<EdgeNode> squareGridGraphEdgesCorners(int i, int j){
        List<EdgeNode> tempEdgeList = new ArrayList<>();
        EdgeNode tempEdge;
        if (m > 1) {
            tempEdge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT,graph[i+1][j])
            tempEdgeList.add();
        }
    }

    private List<EdgeNode> squareGridGraphEdgesBorders(){

    }

    private List<EdgeNode> squareGridGraphEdgesCenters(){

    }

    private void squareGridGraphEdgeAssignation(){
        List<EdgeNode> tempEdgeList;

        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j++){
                //UP_LEFT_CORNER
                if (graph[i][j].x == 0 && graph[i][j].y == 0) {
                    tempEdgeList = new ArrayList<>();
                    if (m > 1) {
                        tempEdge = new EdgeNode(NodeConstants.SQUARE_GRID_LINE_WEIGHT,graph[i+1][j])
                        tempEdgeList.add()
                    }
                }
                //UP_RIGHT_CORNER
                else if (graph[i][j].x == 0 && graph[i][j].y == n - 1){
                    tempEdgeList = new EdgeNode[3];
                }
                else if (graph[i][j].x == m - 1 && graph[i][j].y == 0) {
                    tempEdgeList = new EdgeNode[3];
                    //return SquareGridPositions.DOWN_LEFT_CORNER;
                }
                else if (graph[i][j].x == m - 1 && graph[i][j].y == n - 1) {
                    tempEdgeList = new EdgeNode[3];
                    //return SquareGridPositions.DOWN_RIGHT_CORNER;
                }
                else if (graph[i][j].x == 0) {
                    tempEdgeList = new EdgeNode[5];
                    //return SquareGridPositions.NORTH_BORDER;
                }
                else if (graph[i][j].x == m - 1) {
                    tempEdgeList = new EdgeNode[5];
                    //return SquareGridPositions.SOUTH_BORDER;
                }
                else if (graph[i][j].y == 0) {
                    tempEdgeList = new EdgeNode[5];
                    //return SquareGridPositions.WEST_BORDER;
                }
                else if (graph[i][j].y == n - 1) {
                    tempEdgeList = new EdgeNode[5];
                    //return SquareGridPositions.EAST_BORDER;
                }
                else {
                    tempEdgeList = new EdgeNode[8];
                    //return SquareGridPositions.CENTER;
                }
            }
        }
    }

    public void squareGridGraphCreation(){
        if (m < 0|| n < 0)
            return;

        graph = null;

        newSquareGridGraph();
        int cont = 0;
        SquareGridPositions pos;

        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j++){

                squareGridGraphEdgeAssignation();
                cont++;
            }
        }
    }

    protected VertexNode[][] getSquareGridGraph(){ return graph; }
}
