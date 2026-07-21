package edu.algoritmo_astar.map;

public class PrintMap {
    public PrintMap(int[][] matrix, int n, int m){
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                IO.print(String.valueOf(matrix[i][j]));
            }
            IO.print("\n");
        }
    }
}
