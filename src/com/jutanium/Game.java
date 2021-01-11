package com.jutanium;

import java.awt.*;
import java.util.ArrayList;
import com.jutanium.Cell;

public class Game {
    private ArrayList<Cell> changedCells;
    private ArrayList<Cell> cells;

    public final int numRows;
    public final int numColumns;

    public Game(int numRows, int numColumns, Point[] initialLiving) {
        this(numRows, numColumns);
        for (Point point : initialLiving)
            this.changeCell(point.x, point.y, true);
    }

    public Game(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cells = new ArrayList<>();
        this.changedCells = new ArrayList<>();
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numColumns; x++) {
                cells.add(new Cell(x, y, false));
            }
        }
    }

    public ArrayList<Cell> nextState() {
        ArrayList<Cell> updatedCells = new ArrayList<>();
        for (Cell cell : changedCells) {
            updatedCells.addAll(updateNeighbors(cell));
        }
        ArrayList<Cell> changedCells = new ArrayList<>();
        for (Cell cell : updatedCells) {
            if (cell.alive) {
                if (cell.numNeighbors < 2 || cell.numNeighbors > 3) {
                    cell.alive = false;
                    changedCells.add(cell);
                }
            } else if (cell.numNeighbors == 3) {
                cell.alive = true;
                changedCells.add(cell);
            }
        }
        this.changedCells = changedCells;
        return cells;
    }

    /**
     * @param cell Cell to check; if alive, update its neighbors
     * @return neighbors that were updated
     */
    private ArrayList<Cell> updateNeighbors(Cell cell) {
        int[][] offsets = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0},  /*me!*/  {1, 0},
                {-1, 1}, {0, 1}, {1, 1}
        };
        ArrayList<Cell> updated = new ArrayList<>();
        for (int[] offset : offsets) {
            int deltaX = offset[0];
            int deltaY = offset[1];
            Cell neighbor = getCell(cell.x + deltaX, cell.y + deltaY);
            if (neighbor != null) {
                if (cell.alive) {
                    neighbor.numNeighbors++;
                } else {
                    neighbor.numNeighbors--;
                }

                updated.add(neighbor);
            }
        }
        return updated;
    }

    /**
     * @param x x-coord of cell to get
     * @param y y-coord of cell to get
     * @return the requested cell, or null if the coords are out of bounds
     */
    private Cell getCell(int x, int y) {
        if (x >= 0 && x < numColumns && y >= 0 && y < numRows) {
            return cells.get(numColumns * y + x);
        }
        return null;

    }

    public void changeCell(int x, int y, boolean alive) {
        Cell cell = getCell(x, y);
        if (cell != null)
            cell.alive = alive;
        this.changedCells.add(cell);
    }

    public void printBoard(char aliveChar, char deadChar) {
        for (int y = 0; y < numRows; y++) {
           for (int x = 0; x < numColumns; x++)  {
               System.out.print(getCell(x,y).alive ? aliveChar : deadChar);
           }
           System.out.println();
        }
    }

}
