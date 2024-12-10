package net.xdev789.day9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Drive drive = new Drive();

        Scanner scanner = new Scanner(new FileInputStream("input/day9/task2.txt"));

        int file = 0;
        boolean isEmpty = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            char[] data = line.toCharArray();
            for (char c : data) {
                int len = Character.getNumericValue(c);

                drive.addBlock(isEmpty ? -1 : file / 2, len);

                file++;
                isEmpty = !isEmpty;
            }
        }

        drive.runDefragmentation();

        System.out.println(drive.checksum());
    }
}
