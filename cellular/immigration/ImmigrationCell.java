package cellular.immigration;

import cellular.ICellType;

import java.awt.*;

/**
 * Represents the state of a cell in the immigration game.
 */
public class ImmigrationCell implements ICellType {
    private final int maxState;

    public ImmigrationCell(int maxState) {
        this.maxState = maxState;
    }

    public Color getColor(int state) {
        // Returns a gray scale color
        return new Color(255 - 255/ maxState *state, 255 - 255/ maxState *state, 255 - 255/ maxState *state);
    }
}
