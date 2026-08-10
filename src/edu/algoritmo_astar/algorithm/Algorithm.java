package edu.algoritmo_astar.algorithm;

import edu.algoritmo_astar.graph.CreationSquareGridGraph;
import edu.algoritmo_astar.graph.EdgeNode;
import edu.algoritmo_astar.graph.NodeConstants;
import edu.algoritmo_astar.graph.VertexNode;
import edu.algoritmo_astar.map.CreationMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Algorithm {
    private CreationMap map;
    private CreationSquareGridGraph graph;
    private List<EdgeNode> openList;
    private List<EdgeNode> closeList;
    private EdgeNode solution;

    private VertexNode goal;
    private VertexNode start;

    public Algorithm(CreationMap map, CreationSquareGridGraph graph){
        this.map = map;
        this.graph = graph;

        openList = new ArrayList<>();
        closeList = new ArrayList<>();

        goal = findGoalNode();
        start = findStartNode();

        AStarAlgorithm();
    }

    private VertexNode findGoalNode(){
        if (map == null || graph == null)
            return null;

        for (int i = 0; i < map.getM(); i++){
            for (int j = 0; j < map.getN(); j++){
                if (map.getMatrix()[i][j] == NodeConstants.GOAL)
                    return graph.getNode(i,j);
            }
        }

        return null;
    }

    private VertexNode findStartNode(){
        if (map == null || graph == null)
            return null;

        for (int i = 0; i < map.getM(); i++){
            for (int j = 0; j < map.getN(); j++){
                if (map.getMatrix()[i][j] == NodeConstants.START)
                    return graph.getNode(i,j);
            }
        }

        return null;
    }

    private void AStarAlgorithm(){
        if (start == null || goal == null)
            return;

        EdgeNode beginning = new EdgeNode(0,start,null);
        EdgeNode actual, successor;

        openList.add(beginning);

        while (!openList.isEmpty()){
            openList.sort(Comparator.comparing(EdgeNode::getValueF));

            actual = openList.removeFirst();

            int i = 0;
            int cont;
            boolean isAnotherBetter;
            while (i < actual.pointingTo.edges.size()){
                successor = actual.pointingTo.edges.get(i);

                if (successor.pointingTo == goal){

                    return;
                }

                successor.setValueG(actual);
                successor.setValueH(goal);

                isAnotherBetter = false;

                for (cont = 0; cont < Math.max(openList.size(),closeList.size()); cont++){
                    if (cont < openList.size() && openList.get(cont).getValueF() < successor.getValueF()){
                        isAnotherBetter = true;
                    }
                    if (cont < closeList.size() && closeList.get(cont).getValueF() < successor.getValueF()){
                        isAnotherBetter = true;
                    }
                }

                if (isAnotherBetter)
                    continue;
                else
                    openList.add(successor);

                i++;
            }

            actual.pointingTo.parent = actual.originFrom;
            closeList.add(actual);
        }

        solution = falta añadir como encontrar la solución w.
    }


}


/*      NOTAS:
 * - Los nodos necesitan saber su valor f, g, h.
 *       - h al ser una operacion se definirá como un metodo y no como un atributo.
 *       - g, aún no sé como determinar su valor. Aunque supongo que al estar en una cuadrícula y su movimiento es en
 *         cruz, sus valores son iguales y la diferencia radica en h. Y creo que readica más en si la casilla está en
 *         blanco o si está ocupada.
 *
 *       PASOS PARA HACER EL ALGORITMO
 *
 *   0. PASOS ANTES DE COMENZAR A DESARROLLAR.
 *       Hacer el metodo con la función metahuristica para los nodos aristas.
 *
 *
 *
 *   1. INICIALIZACIÓN
 *       Inicializa las dos listas. Open y Close.
 *       Hallar los nodos inicio y meta.
 *       El nodo inicio lo ingresa directamente a la lista de open.
 *       Se inicializa su variable f en 0.
 *
 *   2. ITERACIONES
 *      Mientras la lista no sea vacía.
 *          Encontrar el nodo con el menor valor de f en la open list y sacarlo.
 *
 *          Para cada arista:
 *              Si el sucesor es END, entonces detern el algoritmo.
 *
 *              Computar sus respectivos valores de g, g y f.
 *              Comprobar si un nodoArista en la misma posisción que el suscesor se encuentra en la open list y
 *              tiene un valor de f es más bajo, entonces NO se agrega a la OPENLIST.
 *              LO MIMOS PERO EN LA CLOSE LIST.
 *              Sino, Agregalo a la openlist.
 *
 *          Coloca al nodo padre en la closedList.
 *
 *   3. RESULTADOS
 *      Hacer una función en printmap que de los resultados.
 *
 *
 *
 *
 *  NOTAS:
 *   Para hacer más facil entontrar el vértice al cual apuntar se debe de ordenar la lista de los nodos vértices dentro del algoritmo.
 *
 */