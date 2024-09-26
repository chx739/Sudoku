package com.example.sudoku;
import java.io.*;

public class Grid implements Serializable {
    // 序列化版本号
    protected static final long serialVersionUID = 1L;
    protected static final int BOX_SIZE = 3;
    protected static final int GRID_SIZE = 9;
    protected int[][] grid;

    public Grid() {
        this.grid = new int[GRID_SIZE][GRID_SIZE];
    }

    public int[] getRow(int row) {
        return grid[row];
    }

    public int[] getColumn(int col) {
        int[] column = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            column[i] = grid[i][col];
        }
        return column;
    }

    public int[] getBox(int row, int col) {
        int[] box = new int[GRID_SIZE];
        int startRow = (row / BOX_SIZE) * BOX_SIZE;
        int startCol = (col / BOX_SIZE) * BOX_SIZE;
        int index = 0;
        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                box[index++] = grid[startRow + i][startCol + j];
            }
        }
        return box;
    }
}
