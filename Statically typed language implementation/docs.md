# 静态类型语言Sudoku文档

## 目录
- [静态类型语言Sudoku文档](#静态类型语言sudoku文档)
  - [目录](#目录)
  - [项目介绍](#项目介绍)
  - [类结构说明](#类结构说明)
    - [Grid 类](#grid-类)
      - [属性](#属性)
      - [主要职责](#主要职责)
    - [Sudoku 类](#sudoku-类)
      - [属性](#属性-1)
      - [主要职责](#主要职责-1)
  - [主要方法介绍](#主要方法介绍)
    - [Grid 类方法](#grid-类方法)
    - [Sudoku 类方法](#sudoku-类方法)
  - [示例与测试说明](#示例与测试说明)
    - [示例代码说明](#示例代码说明)
    - [测试方案](#测试方案)
  - [序列化与反序列化](#序列化与反序列化)

---

## 项目介绍

该数独求解器项目使用静态类型语言*java*实现,由两个主要类组成：`Grid` 和 `Sudoku`。`Grid` 类是基础类，提供了数独棋盘的基本操作与访问方法，而 `Sudoku` 类则继承自 `Grid` 类，扩展了棋盘的解析、候选值推理、对象序列化与反序列化等高级功能。

数独求解器能够通过字符串输入来创建棋盘，并且可以推理所有空单元格的候选值。此外，该程序还支持将 `Sudoku` 对象序列化保存到文件中，以及从文件中反序列化恢复棋盘状态。

---

## 类结构说明

### Grid 类

`Grid` 类是一个基础类，用于表示一个 9x9 的数独棋盘，并提供获取行、列和小方块元素的方法。

#### 属性
- **`BOX_SIZE`**: 小方块的尺寸（3x3）。
- **`GRID_SIZE`**: 整个棋盘的尺寸（9x9）。
- **`grid`**: 存储棋盘内容的二维整型数组。

#### 主要职责
- 提供对棋盘中行、列及 3x3 小方块元素的访问能力。
- 可以作为父类，为 `Sudoku` 类的高级功能提供基础支持。

### Sudoku 类

`Sudoku` 类继承自 `Grid` 类，并且实现了数独棋盘的高级操作，如解析字符串、推理候选值、对象序列化与反序列化等。

#### 属性
继承自 `Grid` 的所有属性。

#### 主要职责
- 将字符串格式的数独棋盘转换为 9x9 的二维整型数组，并存储到 `grid` 属性中。
- 根据当前数独棋盘的状态，推理所有空单元格的候选值，并打印推理结果。
- 支持将当前对象序列化到文件，以及从文件中反序列化恢复对象。

---

## 主要方法介绍

### Grid 类方法

1. **`getRow(int row)`**  
   - 作用：返回指定行的所有元素。
   - 参数：`row` —— 行索引（0-8）。
   - 返回值：指定行的整型数组。

2. **`getColumn(int col)`**  
   - 作用：返回指定列的所有元素。
   - 参数：`col` —— 列索引（0-8）。
   - 返回值：指定列的整型数组。

3. **`getBox(int row, int col)`**  
   - 作用：返回指定单元格 (row, col) 所在 3x3 小方块的所有元素。
   - 参数：
     - `row` —— 单元格所在行索引（0-8）。
     - `col` —— 单元格所在列索引（0-8）。
   - 返回值：该单元格所在 3x3 小方块的整型数组。

### Sudoku 类方法

1. **`Sudoku(String boardStr)`**  
   - 作用：构造函数，使用字符串格式的数独棋盘初始化 `Sudoku` 对象。
   - 参数：`boardStr` —— 长度为 81 的字符串，代表数独棋盘的初始状态。
   - 说明：该字符串应按照从左到右、从上到下的顺序提供每个单元格的值，0 表示空单元格。

2. **`ArrayList<ArrayList<HashSet<Integer>>> getInference()`**  
   - 作用：推理并返回数独棋盘中每个空单元格（值为 0）的候选值，候选值由当前行、列及小方块中未使用的数字组成。
   - 参数：无。
   - 返回值：二维数组结构的候选值表，其中 `HashSet<Integer>` 存储每个空单元格的候选值。

3. **`void printInference()`**  
   - 作用：打印棋盘中每个单元格的候选值信息。
   - 参数：无。

4. **`void serialize(String filename)`**  
   - 作用：将当前 `Sudoku` 对象序列化并保存到指定文件中。
   - 参数：`filename` —— 保存对象的文件名。

5. **`static Sudoku deserialize(FileInputStream file)`**  
   - 作用：从文件中反序列化恢复 `Sudoku` 对象。
   - 参数：`file` —— 读取对象的文件输入流。
   - 返回值：反序列化得到的 `Sudoku` 对象。

---

## 示例与测试说明

### 示例代码说明
`SudokuTest`类提供了`serializationTest` 、 `deserializationTest` 和 `inferenceTest`方法来实现对Sudoku的功能测试

### 测试方案
- **`public static void deserializationTest(Sudoku sudoku)`**
  该方法测试Sudoku类的反序列化功能

- **`public static void serializationTest()`**
  该方法测试Sudoku类的序列化功能

- **`public static void deserializationTest(Sudoku sudoku)`**
  该方法测试Sudoku类的反序列化功能

---

## 序列化与反序列化

`Sudoku` 类提供了 `serialize()` 和 `deserialize()` 方法来实现对象的持久化存储。

- **`serialize(String filename)`**  
  该方法用于将当前对象序列化并保存到指定文件中。对象可以使用 Java 内置的 `ObjectOutputStream` 和 `FileOutputStream` 进行序列化。

- **`static Sudoku deserialize(FileInputStream file)`**  
  该方法用于从指定文件中反序列化对象。反序列化时，读取文件中的对象流，并恢复 `Sudoku` 对象。

---