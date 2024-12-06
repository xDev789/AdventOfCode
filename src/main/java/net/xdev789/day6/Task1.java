package net.xdev789.day6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day6/task1.txt"));

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

        int[][] grid = gridList.toArray(new int[0][]);

        if (MazeWalker.isNotEscapable(grid, guardX, guardY, guardDirection)) {
            System.out.println("No escape");
            return;
        }

        long sum = Stream.of(grid)
                .map(i -> IntStream.of(i)
                        .filter(i1 -> i1 >= 1)
                        .count()
                )
                .flatMapToLong(LongStream::of)
                .sum();

        System.out.println(sum);
    }
}
