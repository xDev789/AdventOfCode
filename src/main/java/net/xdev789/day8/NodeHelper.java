package net.xdev789.day8;

import java.util.*;

public class NodeHelper {
    public static Set<Node> getAntiNodes(Grid grid, Set<Node> nodes, int limit, boolean includeSelf) {
        Set<Node> antiNodes = new HashSet<>();

        Map<Character, Set<Node>> nodeMap = new HashMap<>();
        for (Node node : nodes) {
            nodeMap.computeIfAbsent(node.frequency(), k -> new HashSet<>()).add(node);
        }

        for (Node src : nodes) {
            Set<Node> dstSet = nodeMap.getOrDefault(src.frequency(), Collections.emptySet());
            for (Node dst : dstSet) {
                if (src.equals(dst)) {
                    continue;
                }

                int diffX = src.x() - dst.x();
                int diffY = src.y() - dst.y();

                int firstAntiNode = includeSelf ? 0 : 1;

                for (int i = firstAntiNode; i <= limit; i++) {
                    int x = src.x() + diffX * i;
                    int y = src.y() + diffY * i;

                    if (x < grid.startX() || x >= grid.endX() || y < grid.startY() || y >= grid.endY()) {
                        break;
                    }

                    antiNodes.add(new Node(x, y, src.frequency()));
                }
            }
        }

        return antiNodes;
    }

    public record Grid(int startX, int startY, int endX, int endY) {

    }

    public record Node(int x, int y, char frequency) {

    }
}
