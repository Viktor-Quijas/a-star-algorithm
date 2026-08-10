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
    private final CreationMap map;
    private final CreationSquareGridGraph graph;
    private final List<VertexNode> openList;
    private final List<VertexNode> closeList;
    private VertexNode solution;

    private final VertexNode goal;
    private final VertexNode start;

    public Algorithm(CreationMap map, CreationSquareGridGraph graph){
        this.map = map;
        this.graph = graph;

        openList = new ArrayList<>();
        closeList = new ArrayList<>();

        goal = findGoalNode();
        start = findStartNode();
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

    public boolean AStarAlgorithm(){
        if (start == null || goal == null)
            return false;

        VertexNode beginning = new VertexNode(start);
        VertexNode actual, successor;
        solution = null;

        openList.add(beginning);

        while (!openList.isEmpty()){
            openList.sort(Comparator.comparing(VertexNode::getValueF));

            actual = openList.removeFirst();

            int indexSuccessor = 0;
            int indexAnotherBetter;
            boolean isAnotherBetter;

            while (indexSuccessor < actual.edges.size()){
                successor = new VertexNode(actual.edges.get(indexSuccessor).pointingTo);
                successor.parent = actual;

                if (successor.id == goal.id){
                    solution = successor;
                    return true;
                }

                if (successor.type == NodeConstants.OCCUPIED){
                    indexSuccessor++;
                    continue;
                }


                successor.setValueG(actual, actual.edges.get(indexSuccessor));
                successor.setValueH(goal);

                isAnotherBetter = false;

                for (indexAnotherBetter = 0; indexAnotherBetter < Math.max(openList.size(),closeList.size()); indexAnotherBetter++){
                    if ((indexAnotherBetter < openList.size() &&
                            openList.get(indexAnotherBetter).id == successor.id &&
                            openList.get(indexAnotherBetter).getValueF() < successor.getValueF())
                            ||
                            (indexAnotherBetter < closeList.size() &&
                            closeList.get(indexAnotherBetter).id == successor.id &&
                            closeList.get(indexAnotherBetter).getValueF() < successor.getValueF())
                    )
                        isAnotherBetter = true;
                }

                if (isAnotherBetter) {
                    indexSuccessor++;
                    continue;
                }
                else
                    openList.add(successor);

                indexSuccessor++;
            }

            closeList.add(actual);
        }
        return false;
    }

    public void printSolution(){
        if (solution == null)
            return;

        VertexNode auxiliar = solution;

        while (auxiliar != null){
            IO.println(auxiliar.id);
            auxiliar = auxiliar.parent;
        }
    }
}