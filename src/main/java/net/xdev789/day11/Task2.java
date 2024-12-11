package net.xdev789.day11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Function<Long, List<Long>>> mutators = List.of(
                i -> i == 0 ? List.of(1L) : List.of(),
                i -> {
                    String str = String.valueOf(i);
                    if (str.length() % 2 != 0)
                        return List.of();

                    long head = Long.parseLong(str.substring(0, str.length() / 2));
                    long tail = Long.parseLong(str.substring(str.length() / 2));

                    return List.of(head, tail);
                },
                i -> List.of(i * 2024L)
        );

        List<Long> stones = new ArrayList<>();

        Scanner scanner = new Scanner(new FileInputStream("input/day11/task2.txt"));

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s");

            for (String part : parts) {
                stones.add(Long.parseLong(part));
            }
        }

        System.out.println(StoneHelper.process(stones, mutators, 75));
    }
}
