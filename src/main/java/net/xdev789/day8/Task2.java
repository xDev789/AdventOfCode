package net.xdev789.day8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day8/task2.txt"));

        Set<NodeHelper.Node> nodes = new HashSet<>();

        int x = 0;
        int y = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            for (x = 0; x < line.length(); x++) {
                char frequency = line.charAt(x);

                if (frequency == '.') {
                    continue;
                }

                nodes.add(new NodeHelper.Node(x, y, frequency));
            }

            y++;
        }

        Set<NodeHelper.Node> antiNodes = NodeHelper.getAntiNodes(new NodeHelper.Grid(0, 0, x, y), nodes, Integer.MAX_VALUE, true).stream()
                .map(i -> new NodeHelper.Node(i.x(), i.y(), '.'))
                .collect(Collectors.toSet());

        System.out.println(antiNodes.size());
    }
}
