package edu.algoritmo_astar.map;
import java.util.Map;
import java.util.Scanner;
import edu.algoritmo_astar.graph.CreationSquareGridGraph;

public class MapInterface {
    int m;
    int n;
    Scanner read;
    CreationMap newMap;
    PrintMap printMap;
    CreationSquareGridGraph graph;

    public void FirstProtocol(){
        getParameters();
        newMap = new CreationMap(m,n);
        printMap = new PrintMap(newMap.getMatrix(),m,n);
        printMap.numberPrint();

        graph = new CreationSquareGridGraph(newMap);
        graph.edgesAreCorrect();
        printNodos();
    }

    public void AlgorithmProtocol(){
        getParameters();
        newMap = new CreationMap(m,n);
        printMap = new PrintMap(newMap.getMatrix(),m,n);
        printMap.numberPrint();

        graph = new CreationSquareGridGraph(newMap);
        graph.edgesAreCorrect();
        printNodos();
    }

    //Solo para observar su comportamiento.
    private void printNodos(){
        IO.println("Grafo \n\n");

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                IO.println("ID: " + String.valueOf(graph.getNode(i,j).id));
                IO.println("TYPE: " + String.valueOf(graph.getNode(i,j).type));
                IO.println("POINTING TO: ");

                for (int k = graph.getNode(i,j).edges.size(); k > 0; k--){
                    IO.println(String.valueOf(graph.getNode(i,j).edges.get(k - 1).pointingTo.id));
                }
            }
        }
    }

    public MapInterface(){
        read = new Scanner(System.in);
    }

    private void getParameters(){
        IO.print("Inserta la cantidad de filas:");
        m = read.nextInt();
        IO.print("Inserta la cantidad de columnas:");
        n = read.nextInt();
    }

}
