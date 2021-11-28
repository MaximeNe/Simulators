package cellular;

import agentsystem.IDisplay;
import agentsystem.IGeneration;
import gui.GUISimulator;
import gui.Rectangle;

import java.awt.*;

public class GridDisplay implements IDisplay<Integer> {
    private final ICellType cellType;
    private final int scale;

    public GridDisplay(ICellType cellType, int scale) {
        this.cellType = cellType;
        this.scale = scale;
    }

    public void display(GUISimulator gui, IGeneration<Integer> gen) {
        // This cast is safe because we just stored an IGridGeneration.
        // We could avoid this cast by putting generics all over AgentSystem?
        IGridGeneration g = (IGridGeneration) gen;
        for (int x = 0; x < g.getWidth(); x++) {
            for (int y = 0; y < g.getHeight(); y++) {
                int cell = g.getWithPoint(new Point(x, y));
                Color color = cellType.getColor(cell);

                gui.addGraphicalElement(
                        new Rectangle(x*scale + scale/2, y*scale + scale/2, Color.black, color, scale));
            }
        }
    }
}
