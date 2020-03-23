package com.engeto;

public class Main {
    static int liveNeighbourCount(boolean[][] grid, int x, int y, int fieldSize) {
        int liveNeighbourCount = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((x + i) < 0 || (x + i) >= fieldSize)
                    continue;
                if ((y + j) < 0 || (y + j) >= fieldSize)
                    continue;
                if ((i == 0) && (j == 0))
                    continue;
                if (grid[x + i][y + j]) {
                    liveNeighbourCount++;
                }
            }
        }
        return liveNeighbourCount;
    }

    public static void main(String[] args) throws InterruptedException {

        int fieldSize = Integer.parseInt(args[0]);

        boolean[][] grid = new boolean[fieldSize][fieldSize];
        grid[5][1] = true;
        grid[6][2] = true;
        grid[6][3] = true;
        grid[5][3] = true;
        grid[4][3] = true;

        for (int counter = 0; counter <= 100; counter++) {
            boolean[][] nextGen = new boolean[fieldSize][fieldSize];
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    boolean actualValue = grid[i][j];
                    if (j == fieldSize - 1)
                        if (actualValue)
                            System.out.println("[*]");
                        else
                            System.out.println("[ ]");
                    else
                    if (actualValue)
                        System.out.print("[*]");
                    else
                        System.out.print("[ ]");
                }
            }
            System.out.println();
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    boolean actualValue = grid[i][j];
                    int liveNeighbourCount = liveNeighbourCount(grid, i, j, fieldSize);
                    if (actualValue) {
                        if (liveNeighbourCount == 2 || liveNeighbourCount == 3) {
                            nextGen[i][j] = grid[i][j];
                        } else {
                            nextGen[i][j] = false;
                        }
                    }
                    if (!actualValue) {
                        if (liveNeighbourCount == 3) {
                            nextGen[i][j] = true;
                        } else {
                            nextGen[i][j] = grid[i][j];
                        }
                    }
                }
            }
            grid = nextGen;
            Thread.sleep(1000);
        }

    }
}
