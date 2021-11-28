package cellular.immigration;

import cellular.GridGeneration;

import java.awt.Point;
import java.util.Map;

public class ImmigrationGeneration extends GridGeneration {
    public ImmigrationGeneration(int width, int height, Map<Point, Integer> startingCells) {
        super(width, height, startingCells);
    }

    public ImmigrationGeneration(ImmigrationGeneration other) {
        super(other);
    }

    @Override
    public ImmigrationGeneration clone() {
        return new ImmigrationGeneration(this);
    }
}
