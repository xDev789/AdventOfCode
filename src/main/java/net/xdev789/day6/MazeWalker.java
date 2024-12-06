package net.xdev789.day6;

import java.util.List;
import java.util.Map;

public class MazeWalker {
    public static final List<String> DIRECTIONS = List.of("^", ">", "v", "<");

    public static final Map<String, Integer> DIRECTION_SHIFTS = Map.of(
            "^", -1,
            "<", -2,
            "v", 1,
            ">", 2
    );

    public static boolean isNotEscapable(int[][] grid, int guardInitialX, int guardInitialY, int guardInitialDirection) {
        int guardX = guardInitialX;
        int guardY = guardInitialY;
        int guardDirection = guardInitialDirection;

        grid[guardY][guardX] = guardDirection + 1;

        while (true) {
            String direction = DIRECTIONS.get(guardDirection);

            int guardNextX = guardX + DIRECTION_SHIFTS.get(direction) / 2;
            int guardNextY = guardY + DIRECTION_SHIFTS.get(direction) % 2;

            if (guardNextY < 0 || guardNextY >= grid.length || guardNextX < 0 || guardNextX >= grid[guardNextY].length) {
                return false;
            }

            if (guardDirection + 1 == grid[guardNextY][guardNextX]) {
                return true;
            }

            if (grid[guardNextY][guardNextX] == -1) {
                guardDirection = (guardDirection + 1) % DIRECTIONS.size();
            } else {
                grid[guardNextY][guardNextX] = guardDirection + 1;

                guardX = guardNextX;
                guardY = guardNextY;
            }
        }
    }
}
