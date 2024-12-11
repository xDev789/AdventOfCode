package net.xdev789.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StoneHelper {
    public static long process(List<Long> stones, List<Function<Long, List<Long>>> mutators, int times) {
        Map<Long, Long> stoneMap = stones.stream()
                .collect(Collectors.toMap(i -> i, i -> 1L));

        for (int time = 0; time < times; time++) {
            mutate(stoneMap, mutators);
        }

        return stoneMap.values().stream()
                .mapToLong(i -> i)
                .sum();
    }

    private static void mutate(Map<Long, Long> stones, List<Function<Long, List<Long>>> mutators) {
        Map<Long, Long> dirtyStones = new HashMap<>();

        for (Map.Entry<Long, Long> entry : stones.entrySet()) {
            if (entry.getValue() == 0)
                continue;

            for (Function<Long, List<Long>> mutator : mutators) {
                List<Long> mutatedStones = mutator.apply(entry.getKey());

                if (mutatedStones.isEmpty())
                    continue;

                dirtyStones.putIfAbsent(entry.getKey(), entry.getValue());
                dirtyStones.computeIfPresent(entry.getKey(), (k, v) -> v - entry.getValue());

                for (Long mutatedStone : mutatedStones) {
                    long val = stones.getOrDefault(mutatedStone, 0L);

                    dirtyStones.putIfAbsent(mutatedStone, val);
                    dirtyStones.computeIfPresent(mutatedStone, (k, v) -> v + entry.getValue());
                }
                break;
            }
        }

        stones.putAll(dirtyStones);
    }
}
