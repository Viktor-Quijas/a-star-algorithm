package edu.algoritmo_astar.map;

public class Mapp {
    CreationMap newMap;
    PrintMap printMap;

    public Mapp(){
        newMap = new CreationMap(10,10);
        int[][] matrix = newMap.getMatrix();

        printMap = new PrintMap(matrix, 10, 10);

        IO.print("holi");
    }
}
