package net.xdev789.day7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<BiFunction<Long, Long, Long>> mutators = List.of(
                Long::sum,
                (a, b) -> a * b,
                (a, b) -> Long.parseLong(String.valueOf(a) + b)
        );

        Scanner scanner = new Scanner(new FileInputStream("input/day7/task2.txt"));

        List<Long> targets = new ArrayList<>();
        List<List<Long>> numbers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] parts = line.split(": ");

            targets.add(Long.parseLong(parts[0]));

            numbers.add(Arrays.stream(parts[1].split(" "))
                    .map(Long::parseLong)
                    .toList());
        }

        long counter = 0;
        for (int i = 0; i < numbers.size(); i++) {
            boolean isValid = MathHelper.getPossibleResults(numbers.get(i), mutators).contains(targets.get(i));

            if (isValid) {
                counter += targets.get(i);
            }
        }

        System.out.println(counter);
    }
}
