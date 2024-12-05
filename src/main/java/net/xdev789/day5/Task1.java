package net.xdev789.day5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input/day5/task1.txt"));

        Set<List<Integer>> orders = new HashSet<>();
        Map<Integer, List<Integer>> dependencies = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains(",")) {
                orders.add(Arrays.stream(line.split(","))
                        .map(i -> Integer.parseInt(i.trim()))
                        .toList());
            }

            if (line.contains("|")) {
                String[] parts = line.split("\\|");

                dependencies.computeIfAbsent(Integer.parseInt(parts[1].trim()), k -> new ArrayList<>())
                        .add(Integer.parseInt(parts[0].trim()));
            }
        }

        int counter = 0;
        for (List<Integer> order : orders) {
            List<Integer> queue = new ArrayList<>(order);

            queue.sort(Comparator.comparing(i -> i, (i1, i2) -> {
                if (Objects.equals(i1, i2))
                    return 0;

                return dependencies.getOrDefault(i1, new ArrayList<>()).contains(i2) ? 1 : -1;
            }));

            if (queue.equals(order)) {
                counter += queue.get(queue.size() / 2);
            }
        }

        System.out.println(counter);
    }
}
