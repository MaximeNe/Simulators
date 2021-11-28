package cellular.schelling;

import agentsystem.IGeneration;
import cellular.IGridGeneration;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SchellingGeneration implements IGridGeneration {
    private final int width;
    private final int height;
    // Agents to point. agents[1] = the position of the first agent.
    private final List<Point> agents;
    // Points to values.
    private final Map<Point, Integer> cells;
    // Which cells are free?
    private final List<Point> freeCells;

    public SchellingGeneration(int width, int height, Map<Point, Integer> cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
        this.freeCells = new ArrayList<>();
        this.agents = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            this.agents.add(null);
        }

        int agentId = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // If the cell is not free, add it to our list.
                Point p = new Point(x, y);
                if (!cells.containsKey(p)) {
                    freeCells.add(p);
                } else {
                    agents.set(agentId, p);
                    agentId++;
                }
            }
        }
    }

    public SchellingGeneration(SchellingGeneration other) {
        this.width = other.width;
        this.height = other.height;
        this.cells = new HashMap<>(other.cells);
        this.freeCells = new ArrayList<>(other.freeCells);
        this.agents = new ArrayList<>(other.agents);
    }

    @Override
    public IGeneration<Integer> clone() {
        return new SchellingGeneration(this);
    }

    @Override
    public int nbAgents() {
        return agents.size();
    }

    @Override
    public Integer get(int i) {
        return getWithPoint(agents.get(i));
    }

    @Override
    public void set(int i, Integer value) {
        setWithPoint(agents.get(i), value);
    }

    @Override
    public int getWithPoint(Point p) {
        return cells.getOrDefault(p, 0);
    }

    @Override
    public void setWithPoint(Point p, int value) {
        cells.put(p, value);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<Integer> getNeighbors(int id) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        Point p = agents.get(id);
        int x = p.x;
        int y = p.y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (x == i && y == j) {
                    continue;
                }

                int neighborX;
                int neighborY;

                if (i < 0) {
                    neighborX = width - 1;
                } else {
                    neighborX = i % width;
                }
                if (j < 0) {
                    neighborY = height - 1;
                } else {
                    neighborY = j % width;
                }

                neighbors.add(getWithPoint(new Point(neighborX, neighborY)));
            }
        }

        return neighbors;
    }

    private Point getFreeCell(Point previousCell) {
        // Get a random habitation.
        int i = (new Random()).nextInt(freeCells.size());
        Point nextCell = freeCells.remove(i);
        freeCells.add(previousCell);

        return nextCell;
    }


    public void move(int i) {
        Point oldCell = agents.get(i);
        int oldValue = getWithPoint(oldCell);
        Point newCell = getFreeCell(oldCell);

        setWithPoint(oldCell, SchellingState.FREE);
        cells.remove(oldCell);
        setWithPoint(newCell, oldValue);
        agents.set(i, newCell);
    }
}
