package net.xdev789.day4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static net.xdev789.day4.PathVisitor.visit;

public class Task1 {
    private static final String XMAS = "XMAS";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day4/task1.txt"));

        List<PathVisitor.Position> directions = List.of(
                new PathVisitor.Position(-1, -1),
                new PathVisitor.Position(-1, 0),
                new PathVisitor.Position(-1, 1),
                new PathVisitor.Position(0, -1),
                new PathVisitor.Position(0, 1),
                new PathVisitor.Position(1, -1),
                new PathVisitor.Position(1, 0),
                new PathVisitor.Position(1, 1)
        );

        List<char[]> dataList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            dataList.add(scanner.nextLine().toCharArray());
        }

        char[][] data = dataList.toArray(new char[0][]);

        int counter = 0;

        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                for (PathVisitor.Position direction : directions) {
                    String res = visit(data, new PathVisitor.Position(x, y), direction, 3);

                    if (res.equals(XMAS)) {
                        counter += 1;
                    }
                }
            }
        }

        System.out.println(counter);
    }
}
