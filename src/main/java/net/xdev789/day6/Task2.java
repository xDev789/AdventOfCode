package net.xdev789.day6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day6/task2.txt"));

        int guardX = 0;
        int guardY = 0;
        int guardDirection = -1;

        List<int[]> gridList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();

            Optional<String> guardCharacter = MazeWalker.DIRECTIONS.stream()
                    .filter(row::contains)
                    .findFirst();

            if (guardCharacter.isPresent()) {
                guardY = gridList.size();
                guardX = row.indexOf(guardCharacter.get());

                guardDirection = MazeWalker.DIRECTIONS.indexOf(guardCharacter.get());
            }

            gridList.add(row.chars()
                    .map(c -> (char) c)
                    .map(i -> i == '#' ? -1 : 0)
                    .toArray()
            );
        }

        int[][] initialGrid = gridList.toArray(new int[0][]);

        int counter = 0;
        for (int y = 0; y < initialGrid.length; y++) {
            for (int x = 0; x < initialGrid[y].length; x++) {
                int[][] grid = Arrays.stream(initialGrid)
                        .map(int[]::clone)
                        .toArray(int[][]::new);

                if (grid[y][x] == 0 && (x != guardX || y != guardY)) {
                    grid[y][x] = -1;
                }

                if (MazeWalker.isNotEscapable(grid, guardX, guardY, guardDirection)) {
                    counter += 1;
                }
            }
        }

        System.out.println(counter);
    }
}
