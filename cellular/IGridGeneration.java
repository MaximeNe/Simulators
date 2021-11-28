package cellular;

import agentsystem.IGeneration;

import java.awt.*;
import java.util.List;

/**
 * A generation for a cellular-based simulation.
 */
public interface IGridGeneration extends IGeneration<Integer> {
    /**
     * Get the value at point p.
     * @param p The point.
     * @return The value.
     */
    int getWithPoint(Point p);

    /**
     * Set the value at point p.
     * @param p The point.
     * @param value The value.
     */
    void setWithPoint(Point p, int value);

    /**
     * Get the width of this generation.
     * @return The width.
     */
    int getWidth();

    /**
     * Get the height of this generation.
     * @return The height.
     */
    int getHeight();

    /**
     * Get the neighbors of this agent, in an ordred list from top-left
     * to bottom-right:
     *
     * <pre>
     *  1    2    3
     *  4  cell   5
     *  6    7    8
     * </pre>
     *
     * @return A list of neighbors.
     */
    List<Integer> getNeighbors(int id);
}
