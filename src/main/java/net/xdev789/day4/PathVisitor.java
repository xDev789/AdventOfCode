package net.xdev789.day4;

public class PathVisitor {
    public static String visit(char[][] data, Position start, Position direction, int depth) {
        if (start.x() + direction.x() * depth < 0 || start.x() + direction.x() * depth >= data.length) {
            return "";
        }
        if (start.y() + direction.y() * depth < 0 || start.y() + direction.y() * depth >= data[0].length) {
            return "";
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i <= depth; i++) {
            res.append(data[start.x() + i * direction.x()][start.y() + i * direction.y()]);
        }

        return res.toString();
    }
    
    public record Position(int x, int y) {

    }
}
