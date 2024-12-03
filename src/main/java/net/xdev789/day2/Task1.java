package net.xdev789.day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day2/task1.txt"));

        int counter = 0;

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");

            List<Integer> diffs = new ArrayList<>();

            for (int i = 1; i < parts.length; i++) {
                diffs.add(Integer.parseInt(parts[i - 1]) - Integer.parseInt(parts[i]));
            }

            if (isValid(diffs)) {
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
