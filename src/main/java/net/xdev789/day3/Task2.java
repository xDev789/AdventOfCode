package net.xdev789.day3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day3/task2.txt"));

        long counter = 0;

        boolean enable = true;
        while (scanner.hasNextLine()) {
            Pattern pattern = Pattern.compile("mul\\((-?\\d+),(-?\\d+)\\)|do\\(\\)|don't\\(\\)");
            Matcher matcher = pattern.matcher(scanner.nextLine());

            while (matcher.find()) {
                String operation = matcher.group();

                String opcode = operation.split("\\(")[0];

                switch (opcode) {
                    case "do":
                        enable = true;
                        break;
                    case "don't":
                        enable = false;
                        break;
                    case "mul":
                        if (!enable)
                            break;

                        String[] data = operation.replaceAll("[^0-9,-]", "").split(",");

                        counter += Long.parseLong(data[0]) * Long.parseLong(data[1]);
                        break;
                }
            }
        }

        System.out.println(counter);
    }
}
