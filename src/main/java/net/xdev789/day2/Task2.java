package net.xdev789.day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day2/task2.txt"));

        int counter = 0;

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");

            List<List<Integer>> diffs = new ArrayList<>();

            for (int i = 0; i < parts.length; i++) {
                List<Integer> data = Stream.concat(
                                Arrays.stream(Arrays.copyOfRange(parts, 0, i)),
                                Arrays.stream(Arrays.copyOfRange(parts, i + 1, parts.length)))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .toList();

                List<Integer> diff = new ArrayList<>();

                for (int j = 1; j < data.size(); j++) {
                    diff.add(data.get(j - 1) - data.get(j));
                }

                diffs.add(diff);
            }

            if (diffs.stream().anyMatch(Task2::isValid)) {
                counter += 1;
            }
        }

        System.out.println(counter);
    }

    private static boolean isValid(List<Integer> diffs) {
        if (!diffs.stream().allMatch(diff -> diff < 0) && !diffs.stream().allMatch(diff -> diff > 0)) {
            return false;
        }

        return diffs.stream().noneMatch(diff -> Math.abs(diff) > 3);
    }
}
