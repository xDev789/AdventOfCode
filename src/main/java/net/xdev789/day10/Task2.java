package net.xdev789.day10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day10/task2.txt"));

        List<PathWalker.Position> starts = new ArrayList<>();
        List<PathWalker.Position> finishes = new ArrayList<>();

        List<int[]> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int[] row = new int[line.length()];

            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int n = Character.getNumericValue(chars[i]);

                if (n == 0)
                    starts.add(new PathWalker.Position(data.size(), i));
                if (n == 9)
                    finishes.add(new PathWalker.Position(data.size(), i));

                row[i] = n;
            }

            data.add(row);
        }

        int[][] grid = data.toArray(new int[0][]);

        long result = starts.parallelStream()
                .mapToLong(start -> finishes.stream()
                        .map(finish -> PathWalker.getPathSet(grid, start, finish).size())
                        .reduce(0, Integer::sum)
                )
                .sum();

        System.out.println(result);
    }
}
