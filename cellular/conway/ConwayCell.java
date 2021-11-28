package cellular.conway;

import cellular.ICellType;

import java.awt.Color;

/**
 * Represents the state of a cell in Conway's game of life.
 */
public class ConwayCell implements ICellType {
    public static int DEAD = 0;
    public static int ALIVE = 1;

    @Override
    public Color getColor(int state) {
        if (state == 0) {
            return Color.white;
        } else {
            return Color.black;
        }
    }
}