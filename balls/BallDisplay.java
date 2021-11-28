package balls;

import agentsystem.IDisplay;
import agentsystem.IGeneration;
import gui.GUISimulator;
import gui.Oval;

import java.awt.*;

public class BallDisplay implements IDisplay<Ball> {
    @Override
    public void display(GUISimulator gui, IGeneration<Ball> g) {
        int nbAgents = g.nbAgents();
        for (int i = 0; i < nbAgents; i++) {
            Point p = g.get(i).position();
            gui.addGraphicalElement(new Oval(p.x, p.y, Color.WHITE, Color.WHITE, 10));
        }
    }
}
