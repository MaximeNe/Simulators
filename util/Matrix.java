package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A 2D matrix of ints.
 */
public class Matrix {
    private final int[][] array;
    private final int width;
    private final int height;

    public Matrix(int width, int height) {
        this.array = new int[height][width];
        this.width = width;
        this.height = height;
    }

    public Matrix(Matrix other) {
        this.width = other.width;
        this.height = other.height;
        this.array = new int[height][width];
        for (int y = 0; y <  other.height; y++) {
            for (int x = 0; x < other.width; x++) {
                array[y][x] = other.array[y][x];
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Convert an ID to a 2D coordinate.
     * For example, for a matrix of size 5*5, then id 0 would
     * return Point(0, 0), and id 8 would return Point(2, 1).
     * @param id The id to convert.
     * @return A coordinate equivalent to the id.
     */
    public Point idToPoint(int id) {
        int x = id % width;
        int y = Math.floorDiv(id, width);

        return new Point(x, y);
    }

    /**
     * Get an element.
     * @param p The (x, y) position of the element.
     * @return The element.
     * @throws IndexOutOfBoundsException if the indexes are out of bounds.
     */
    public int get(Point p) {
        return array[p.y][p.x];
    }

    /**
     * Set one of the values to an element.
     * @param p The (x, y) position of the element.
     * @param value The element to set at.
     * @throws IndexOutOfBoundsException if the indexes are out of bounds.
     */
    public void set(Point p, int value) {
        array[p.y][p.x] = value;
    }

    /**
     * Return the neighbors of a given cell.
     * @param p The (x, y) position of the cell.
     * @return A list of eight elements, being the neighbors.
     * returned in an ordered manner from top-left to bottom-right:
     *  1    2    3
     *  4  cell   5
     *  6    7    8
     *  The matrix is circular: if the requested is at the sides
     *  of the matrix (such as the top-right), then the neighbors will
     *  wrap around.
     */
    public List<Integer> getNeighbors(Point p) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int x = p.x;
        int y = p.y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (x == i && y == j) {
                    continue;
                }

                // Wrap around
                int neighborX = i < 0 ? width - 1 : i % width;
                int neighborY = j < 0 ? height - 1 : j % height;

                neighbors.add(get(new Point(neighborX, neighborY)));
            }
        }

        return neighbors;
    }
}
