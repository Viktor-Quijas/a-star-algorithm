package edu.algoritmo_astar.map;

import java.util.Random;

public class CreationMap {
    private final int m;
    private final int n;
    private final int[][] matrix;

    public int BLANK = 0;
    public int OCCUPIED = 1;
    public int START = 2;
    public int END = 3;

    private void createMap() {
        if (matrix == null)
            return;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = getGender();

                if (i == 0 && j == 0)
                    matrix[i][j] = START;
                else if (i == m - 1 && j == n - 1)
                    matrix[i][j] = END;
            }
        }
    }

    private int getGender(){
        int gender;
        Random r = new Random();
        int x = r.nextInt(100);

        if (x < 20)
            gender = OCCUPIED;
        else {
            gender = BLANK;
        }

        return gender;
    }

    public int[][] getMatrix(){
        return matrix;
    }

    public CreationMap(int m, int n){
        this.m = m;
        this.n = n;
        matrix = new int[m][n];
        createMap();
    }
}

