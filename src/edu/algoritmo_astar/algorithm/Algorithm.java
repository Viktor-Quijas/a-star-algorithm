package edu.algoritmo_astar.algorithm;

import edu.algoritmo_astar.graph.CreationSquareGridGraph;
import edu.algoritmo_astar.map.CreationMap;

public class Algorithm {
    private CreationMap map;
    private CreationSquareGridGraph graph;


}


/*      NOTAS:
* - Los nodos necesitan saber su valor f, g, h.
*       - h al ser una operacion se definirá como un metodo y no como un atributo.
*       - g, aún no sé como determinar su valor. Aunque supongo que al estar en una cuadrícula y su movimiento es en
*         cruz, sus valores son iguales y la diferencia radica en h. Y creo que readica más en si la casilla está en
*         blanco o si está ocupada.
*
*
* */