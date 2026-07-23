package edu.algoritmo_astar.map;
import java.util.Scanner;

public class PublicMap {
    int m;
    int n;
    int[][] matrix;
    Scanner read;
    CreationMap newMap;
    PrintMap printMap;

    public void FirstProtocol(){
        getParameters();
        newMap = new CreationMap(m,n);
        matrix = newMap.getMatrix();
        printMap = new PrintMap(matrix,m,n);
        printMap.numberPrint();
    }

    public PublicMap(){
        read = new Scanner(System.in);
    }

    public void getParameters(){
        IO.print("Inserta la cantidad de filas:");
        m = read.nextInt();
        IO.print("Inserta la cantidad de columnas:");
        n = read.nextInt();
    }
}
