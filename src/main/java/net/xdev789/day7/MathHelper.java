package net.xdev789.day7;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiFunction;

public class MathHelper {
    public static List<Long> getPossibleResults(List<Long> numbers, List<BiFunction<Long, Long, Long>> operations) {
        if (numbers.isEmpty())
            return Collections.emptyList();

        List<Long> results = new LinkedList<>();
        Deque<Entry> entries = new ConcurrentLinkedDeque<>();

        entries.add(new Entry(numbers.getFirst(), 0));
        while (!entries.isEmpty()) {
            Entry entry = entries.poll();

            if (entry.index() == numbers.size() - 1) {
                results.addLast(entry.value());
                continue;
            }

            for (BiFunction<Long, Long, Long> operation : operations) {
                long result = operation.apply(entry.value(), numbers.get(entry.index() + 1));

                entries.addLast(new Entry(result, entry.index() + 1));
            }
        }

        return results;
    }

    private record Entry(long value, int index) {}
}
