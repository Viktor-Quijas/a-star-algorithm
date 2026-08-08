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
 *
 *
 *
 *
 *  NOTAS:
 *   Para hacer más facil entontrar el vértice al cual apuntar se debe de ordenar la lista de los nodos vértices dentro del algoritmo.
 *
 */