package com.example.sudoku.test;
import java.io.*;
import com.example.sudoku.Sudoku;

class SudokuTest {
    private static final String input = "017903600000080000900000507072010430000402070064370250701000065000030000005601720";
    // 序列化文件名
    private static final String serializationFIleName = "sudoku.ser";

    // 序列化测试
    public static void serializationTest(Sudoku sudoku) {
        try {
            sudoku.serialize(serializationFIleName);
            System.out.println("Object is serialized");
            System.out.println("Serialization test passed");
        } catch (Exception e) {
            System.out.println("Serialization test failed");
            e.printStackTrace();
        }
    }

    // 反序列化测试
    public static void deserializationTest() {
        try {
            Sudoku.deserialize(new FileInputStream(serializationFIleName));
            System.out.println("deserialization test passed");
        } catch (Exception e) {
            System.out.println("deserialization test failed");
            e.printStackTrace();
        }
    }

    // 推理测试
    public static void inferenceTest(Sudoku sudoku) {
        try {
            sudoku.printInference();
            System.out.println("inference test passed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(input);
        serializationTest(sudoku);
        deserializationTest();
        inferenceTest(sudoku);
    }
}