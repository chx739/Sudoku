package com.example.sudoku;
import java.io.*;
import java.util.*;

public class Sudoku extends Grid {
    public Sudoku(String boardStr) {
        super();
        parse(boardStr);
    }
    
    private void parse(String boardStr) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = Character.getNumericValue(boardStr.charAt(i * GRID_SIZE + j));
            }
        }
    }
    //推理棋盘，得到各单元格候选值
    public ArrayList<ArrayList<HashSet<Integer>>> getInference() {
        ArrayList<ArrayList<HashSet<Integer>>> inference = new ArrayList<>();
        for (int row = 0; row < GRID_SIZE; row++) {
            ArrayList<HashSet<Integer>> rowArr = new ArrayList<>();
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 0) {
                    rowArr.add(getCandidates(row, col));
                } else {
                    rowArr.add(new HashSet<>());
                }
            }
            inference.add(rowArr);
        }
        return inference;
    }
    //获取当前单元格的候选值
    private HashSet<Integer> getCandidates(int row, int col) {
        // 排除当前行、列、小方块中已存在的数字
        HashSet<Integer> candidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int value : getRow(row)) {
            candidates.remove(value);
        }
        for (int value : getColumn(col)) {
            candidates.remove(value);
        }
        for (int value : getBox(row, col)) {
            candidates.remove(value);
        }
        return candidates; // 返回候选值
    }
    //打印推理结果
    public void printInference() {
        var inference = getInference();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.println("row: " + i + "  col: " + j + "  candidates:" + inference.get(i).get(j));
            }
        }
    }
    //序列化对象
    public void serialize(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //反序列化
    public static Sudoku deserialize(FileInputStream file){
        try (ObjectInputStream ois = new ObjectInputStream(file)){
            return (Sudoku) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
