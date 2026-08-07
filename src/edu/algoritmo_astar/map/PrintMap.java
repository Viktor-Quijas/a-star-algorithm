package edu.algoritmo_astar.map;

public class PrintMap {
    private int [][] matrix;
    int n;
    int m;


    public PrintMap(int[][] matrix, int m, int n){
        this.matrix = matrix;
        this.n = n;
        this.m = m;
    }

    public void numberPrint(){
        if(matrix == null)
            return;

        IO.println("Descrito por números.");

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                IO.print(String.valueOf(matrix[i][j]));
            }
            IO.print("\n");
        }
    }
}
