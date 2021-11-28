package agentsystem;

import gui.GUISimulator;

/**
 * Displays some objects to a graphical interface.
 */
public interface IDisplay<T> {
    /**
     * Display this given interface to the screen.
     * @param gui The graphical interface.
     * @param gen The generation to display.
     */
    void display(GUISimulator gui, IGeneration<T> gen);
}
