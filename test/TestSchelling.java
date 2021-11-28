package test;

import agentsystem.SingleRuleSystem;
import cellular.GridDisplay;
import cellular.schelling.SchellingGeneration;
import cellular.schelling.SchellingRule;
import cellular.schelling.SchellingState;
import gui.GUISimulator;

import java.awt.Point;
import java.awt.Color;
import java.util.*;

public class TestSchelling {
    public static void main(String[] args) {
        int height = 50;
        int width  = 50;
        int scale  = 15;
        int maxFamily = 6;
        int k = 5;

        GUISimulator gui = new GUISimulator(width * scale,
                height * scale, Color.white);

        Map<Point, Integer> startingCells = new HashMap<>();

        Random rand = new Random();
        // Leave some space (height-1)
        for (int y = 0; y < height ; y++) {
            for (int x = 0; x < (width-width/5); x++) {
                startingCells.put(new Point(x, y), rand.nextInt(maxFamily) + 1);
            }
        }

        SchellingGeneration g = new SchellingGeneration(width, height, startingCells);
        SchellingRule r = new SchellingRule(k);
        SchellingState cellType = new SchellingState();
        GridDisplay display = new GridDisplay(cellType, scale);
        SingleRuleSystem<Integer> automata = new SingleRuleSystem<>(gui, g, r, display);

        gui.setSimulable(automata);
    }
}
