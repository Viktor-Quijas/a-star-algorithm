package edu.algoritmo_astar.map;

import java.util.Random;
import java.util.Scanner;

public class CreationMap {
    private final int m;
    private final int n;
    private final int[][] matrix;

    public void createRandomMap() {
        if (matrix == null)
            return;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = getGender();

                if (i == 0 && j == 0)
                    matrix[i][j] = MapConstants.START;
                else if (i == m - 1 && j == n - 1)
                    matrix[i][j] = MapConstants.GOAL;
            }
        }
    }

    private int getGender(){
        int gender;
        Random r = new Random();
        int x = r.nextInt(100);

        if (x < 33)
            gender = MapConstants.OCCUPIED;
        else {
            gender = MapConstants.BLANK;
        }

        return gender;
    }

    public void fillMatrixFromInput() {
        Scanner read = new Scanner(System.in);

        System.out.println("Ingrese las " + m + " filas del mapa (" + n + " dígitos por fila):");

        for (int i = 0; i < m; i++) {
            String line = read.next().trim();

            // Validación de seguridad contra tropas desproporcionadas
            while (line.length() != n) {
                System.out.println("⚠️ Falla de longitud: La fila debe tener exactamente " + n + " dígitos. Reingrese la fila " + i + ":");
                line = read.next().trim();
            }

            for (int j = 0; j < n; j++) {
                // Capturamos el carácter y lo convertimos a entero
                char digitChar = line.charAt(j);
                this.matrix[i][j] = Character.getNumericValue(digitChar);
            }
        }
    }

    public int[][] getMatrix(){
        return matrix;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public CreationMap(int m, int n){
        this.m = m;
        this.n = n;
        matrix = new int[m][n];
    }
}

