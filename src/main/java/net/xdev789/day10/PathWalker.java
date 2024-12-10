package net.xdev789.day10;

import java.util.*;
import java.util.stream.Collectors;

public class PathWalker {
    public static Set<List<Position>> getPathSet(int[][] grid, Position src, Position dst) {
        Set<List<Position>> paths = new HashSet<>();

        List<Position> trace = new LinkedList<>();
        trace.add(src);

        getPathSetRecursively(grid, dst, trace, paths);

        return paths;
    }

    private static void getPathSetRecursively(int[][] grid, Position dst, List<Position> trace, Set<List<Position>> paths) {
        Position current = trace.getLast();
        if (current.equals(dst)) {
            paths.add(List.copyOf(trace));
        }

        Set<Position> nextPositions = Set.of(
                new Position(current.x() - 1, current.y()),
                new Position(current.x() + 1, current.y()),
                new Position(current.x(), current.y() - 1),
                new Position(current.x(), current.y() + 1)
        ).stream()
                .filter(next -> isValid(grid, current, next))
                .collect(Collectors.toSet());

        for (Position nextPosition : nextPositions) {
            trace.add(nextPosition);

            getPathSetRecursively(grid, dst, trace, paths);
        }

        trace.removeLast();
    }

    private static boolean isValid(int[][] grid, Position current, Position next) {
        if (next.x() >= 0 && next.x() < grid.length && next.y() >= 0 && next.y() < grid[0].length) {
            return grid[next.x()][next.y()] - grid[current.x()][current.y()] == 1;
        }

        return false;
    }

    public record Position(int x, int y) {

    }
}
