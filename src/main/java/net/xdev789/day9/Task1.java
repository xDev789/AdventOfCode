package net.xdev789.day9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Drive drive = new Drive();

        Scanner scanner = new Scanner(new FileInputStream("input/day9/task1.txt"));

        int file = 0;
        boolean isEmpty = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            char[] data = line.toCharArray();
            for (char c : data) {
                int len = Character.getNumericValue(c);

                for (int i = 0; i < len; i++) {
                    drive.addBlock(isEmpty ? -1 : file / 2, 1);
                }

                file++;
                isEmpty = !isEmpty;
            }
        }

        drive.runDefragmentation();

        System.out.println(drive.checksum());
    }
}
