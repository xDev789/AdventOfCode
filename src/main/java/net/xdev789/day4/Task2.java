package net.xdev789.day4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static net.xdev789.day4.PathVisitor.visit;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day4/task2.txt"));

        List<char[]> dataList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            dataList.add(scanner.nextLine().toCharArray());
        }

        char[][] data = dataList.toArray(new char[0][]);

        int counter = 0;

        for (int x = 0; x < data.length - 2; x++) {
            for (int y = 0; y < data[x].length - 2; y++) {
                String line1 = visit(data, new PathVisitor.Position(x, y), new PathVisitor.Position(1, 1), 2);
                String line2 = visit(data, new PathVisitor.Position(x + 2, y), new PathVisitor.Position(-1, 1), 2);

                if (!line1.equals("MAS") && !line1.equals("SAM"))
                    continue;
                if (!line2.equals("MAS") && !line2.equals("SAM"))
                    continue;

                counter += 1;
            }
        }

        System.out.println(counter);
    }
}
