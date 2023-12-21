package com.example.caculator;

import java.util.Random;

public class GameData {
    private int Rows;
    private int Columns;
    private int numberOfBombs;
    private boolean[][] mineField; // Represents the grid with mines
    private int[][] surroundingMines; // Stores the number of surrounding mines
    public GameData(int rows, int cols) {
        this.Rows = rows;
        this.Columns = cols;
        this.numberOfBombs = Math.max((int) (rows * cols / 6.4), 1);

        this.mineField = new boolean[rows][cols];
        this.surroundingMines = new int[rows][cols];

        generateMines();
    }

    private void generateMines() {
        for (int i = 0; i < this.Rows; i++)
        {
            for (int j = 0; j < this.Columns; j++)
            {
                this.mineField[i][j] = false;
                this.surroundingMines[i][j] = 0;
            }
        }

        int cnt = 0;
        Random random = new Random();
        while (cnt < this.numberOfBombs)
        {
            int row = random.nextInt(this.Rows);
            int col = random.nextInt(this.Columns);
            if (isValidPosition(row, col) == false) continue;
            if (this.mineField[row][col] == true) continue;

            cnt++;
            this.mineField[row][col] = true;
        }

        for (int i = 0; i < this.Rows; i++)
        {
            for (int j = 0; j < this.Columns; j++)
            {
                if (this.mineField[i][j] == true)
                {
                    this.surroundingMines[i][j] = -1;
                    continue;
                }

                int surroundingMineCount = 0;
                for (int dr = -1; dr <= 1; dr++)
                {
                    for (int dc = -1; dc <= 1; dc++)
                    {
                        int newRow = i + dr;
                        int newCol = j + dc;
                        if (isValidPosition(newRow, newCol) && mineField[newRow][newCol]) surroundingMineCount++;

                    }
                }
                this.surroundingMines[i][j] = surroundingMineCount;
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        if (row < 0 || row >= this.Rows || col < 0 || col >= this.Columns) return false;
        return true;
    }

    public int getNumberOfRows() {
        return Rows;
    }

    public int getNumberOfColumns() {
        return Columns;
    }

    public String getDataAtCell(int i, int j) {
        String[] matchNumber = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight"};
        if (this.surroundingMines[i][j] == -1) return "bomb";
        return matchNumber[this.surroundingMines[i][j]];
    }
}
