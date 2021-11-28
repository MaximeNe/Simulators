package boids;

import agentsystem.IDisplay;
import agentsystem.IGeneration;
import agentsystem.MultiRuleEntry;
import gui.GUISimulator;
import gui.Oval;
import util.Range;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class BoidDisplay implements IDisplay<Boid> {
    private final Range[] agentGroups;
    // Each group has a different color.
    private static final Color[] COLOR_RANGE = {
            Color.blue,
            Color.red,
            Color.black,
            Color.orange,
            Color.green,
            Color.magenta,
            Color.cyan
    };
    private float[][] placesToAvoid;

    private Color getColor(int i) {
        return COLOR_RANGE[i % COLOR_RANGE.length];
    }

    public BoidDisplay(List<MultiRuleEntry<Boid>> ruleEntries) {
        // We want to color the agent groups when displaying them.
        this.agentGroups = new Range[ruleEntries.size()];
        for (int i = 0; i < ruleEntries.size(); i++) {
            Range agentRange = ruleEntries.get(i).rule().agentRange();
            agentGroups[i] = agentRange;
        }
    }

    @Override
    public void display(GUISimulator gui, IGeneration<Boid> gen) {
        for (int group = 0; group < agentGroups.length; group++) {
            Range r = agentGroups[group];
            for (int i = r.start(); i < r.end(); i++) {
                // Add the boid to the GUI
                Boid b = gen.get(i);
                gui.addGraphicalElement(new Triangle(b.getPosition()[0], b.getPosition()[1], getColor(group), getColor(group), 10, b.getSpeed()[0], b.getSpeed()[1]));
            }
        }
    }
}
