package cellular.conway;

import agentsystem.IGeneration;
import cellular.GridGeneration;

import java.awt.Point;
import java.util.Map;

/**
 * A {@link IGeneration} for Conway's game of life.
 */
public class ConwayGeneration extends GridGeneration {
    public ConwayGeneration(int width, int height, Map<Point, Integer> startingCells) {
        super(width, height, startingCells);
    }

    public ConwayGeneration(ConwayGeneration other) {
        super(other);
    }

    public IGeneration<Integer> clone() {
        return new ConwayGeneration(this);
    }
}
