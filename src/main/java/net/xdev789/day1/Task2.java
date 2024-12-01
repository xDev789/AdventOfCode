package net.xdev789.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day1/task2.txt"));

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");

            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);

            map1.put(num1, map1.getOrDefault(num1, 0) + 1);
            map2.put(num2, map2.getOrDefault(num2, 0) + 1);
        }

        int similarity = 0;

        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            similarity += entry.getKey() * entry.getValue() * map2.getOrDefault(entry.getKey(), 0);
        }

        System.out.println(similarity);
    }
}
