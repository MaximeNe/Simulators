package cellular;

import agentsystem.IGeneration;
import util.Matrix;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * A cellular automata state.
 *
 * <p>
 *  It exposes a grid of cells. Each cell in the automata has an
 *  x and y coordinate. The state of each cell is dependent on its
 *  neighbors.
 * </p>
 */
public abstract class GridGeneration implements IGridGeneration {
    private final Matrix matrix;

    public GridGeneration(int width, int height) {
        this.matrix = new Matrix(width, height);
    }
    public GridGeneration(GridGeneration other) {
        this.matrix = new Matrix(other.matrix);
    }

    public GridGeneration(int width, int height, Map<Point, Integer> startingCells) {
        this(width, height);
        for (Map.Entry<Point, Integer> entry: startingCells.entrySet()) {
            matrix.set(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public abstract IGeneration<Integer> clone();

    @Override
    public int getWidth() {
        return matrix.getWidth();
    }

    @Override
    public int getHeight() {
        return matrix.getHeight();
    }

    @Override
    public Integer get(int i) {
        return matrix.get(matrix.idToPoint(i));
    }

    @Override
    public void set(int i, Integer value) {
        matrix.set(matrix.idToPoint(i), value);
    }

    @Override
    public int getWithPoint(Point p) {
        return matrix.get(p);
    }

    @Override
    public int nbAgents() {
        return matrix.getWidth() * matrix.getHeight();
    }

    public void set(int i, int value) {
        matrix.set(matrix.idToPoint(i), value);
    }

    public void setWithPoint(Point p, int value) {
        matrix.set(p, value);
    }

    public List<Integer> getNeighbors(int id) {
        return matrix.getNeighbors(matrix.idToPoint(id));
    }
}
