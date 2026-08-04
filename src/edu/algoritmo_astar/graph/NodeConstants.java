package edu.algoritmo_astar.graph;

public interface NodeConstants {
    public static final int CLOSED_LIST = -1;
    public static final int WAITING = 0;
    public static final int OPEN_LIST = -1;

    public static final int BLANK = 0;
    public static final int OCCUPIED = 1;
    public static final int START = 2;
    public static final int GOAL = 3;

    public static final int SQUARE_GRID_LINE_WEIGHT = 1;
    public static final double SQUARE_GRID_DIAGONAL_WEIGHT = Math.sqrt(2);
}
