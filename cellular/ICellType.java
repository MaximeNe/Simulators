package cellular;

import java.awt.Color;

/**
 * The state of a cell.
 */
public interface ICellType {
    /**
     * Get the color associated with a cell's state, for the purpose of displaying
     *
     * @return The color of the cell.
     */
    Color getColor(int state);
}
