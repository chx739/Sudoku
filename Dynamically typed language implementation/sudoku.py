import pickle
from copy import deepcopy

class Grid:
    BOX_SIZE = 3
    GRID_SIZE = 9
    
    def __init__(self, grid=None):
        # 初始化棋盘为空或传入的grid
        self.grid = deepcopy(grid) if grid else [[0] * self.GRID_SIZE for _ in range(self.GRID_SIZE)]
    
    def getRow(self, row):
        return self.grid[row]
    
    def getColumn(self, col):
        return [self.grid[row][col] for row in range(self.GRID_SIZE)]
    
    def getBox(self, row, col):
        # 计算所属小方块的起始位置
        start_row, start_col = (row // self.BOX_SIZE) * self.BOX_SIZE, (col // self.BOX_SIZE) * self.BOX_SIZE
        return [self.grid[r][c] for r in range(start_row, start_row + self.BOX_SIZE)
                               for c in range(start_col, start_col + self.BOX_SIZE)]


class Sudoku(Grid):
    def __init__(self, board_str=None, grid=None):
        # 通过字符串或者已有grid进行初始化
        if board_str:
            super().__init__(self._parse(board_str))
        else:
            super().__init__(grid)
    
    def _parse(self, board_str):
        # 将输入的字符串转换为二维数组形式
        board = [[int(board_str[i * self.GRID_SIZE + j]) for j in range(self.GRID_SIZE)]
                  for i in range(self.GRID_SIZE)]
        return board
    
    def getInference(self):
        # 推理每个单元格的候选值
        candidates = [[[] for _ in range(self.GRID_SIZE)] for _ in range(self.GRID_SIZE)]
        for row in range(self.GRID_SIZE):
            for col in range(self.GRID_SIZE):
                if self.grid[row][col] == 0:
                    candidates[row][col] = self._get_possible_values(row, col)
        return candidates
    
    def _get_possible_values(self, row, col):
        # 获取某个空单元格的候选值（排除行、列、小方块中已存在的数）
        possible_values = set(range(1, 10))
        used_values = set(self.getRow(row) + self.getColumn(col) + self.getBox(row, col))
        return list(possible_values - used_values)
    
    def __str__(self):
        # 返回棋盘的字符串表示
        return '\n'.join([' '.join(map(str, row)) for row in self.grid])
    
    def __eq__(self, other):
        # 比较两个 Sudoku 对象是否相等
        return isinstance(other, Sudoku) and self.grid == other.grid

    def clone(self):
        # 克隆当前 Sudoku 对象
        return Sudoku(grid=self.grid)
    
    def serialize(self, filename):
        # 将 Sudoku 对象序列化到文件
        with open(filename, 'wb') as f:
            pickle.dump(self, f)
    
    @staticmethod
    def deserialize(filename):
        # 从文件反序列化 Sudoku 对象
        with open(filename, 'rb') as f:
            return pickle.load(f)


# 测试代码
if __name__ == "__main__":
    input = "017903600000080000900000507072010430000402070064370250701000065000030000005601720"
    
    # 创建 Sudoku 对象
    sudoku = Sudoku(input)
    
    # 打印棋盘
    print("原始棋盘:")
    print(sudoku)
    
    # 推理并打印候选值
    print("\n候选值:")
    candidates = sudoku.getInference()
    for row in candidates:
        print(row)
    
    # 序列化和反序列化
    sudoku.serialize('sudoku.pkl')
    deserialized_sudoku = Sudoku.deserialize('sudoku.pkl')
    
    # 比较序列化前后的对象是否相等
    print("\nAre serialization and deserialization equal?:", sudoku == deserialized_sudoku)
    
    # 克隆对象并比较
    cloned_sudoku = sudoku.clone()
    print("Are sudoku equal after cloning?:", sudoku == cloned_sudoku)
