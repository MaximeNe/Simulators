package test;

import agentsystem.MultiRuleEntry;
import agentsystem.MultiRuleSystem;
import boids.*;
import gui.GUISimulator;
import util.Range;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TestBoids {
    public static void main(String[] args) {
        int height = 800;
        int width = 1000;

        // Create a new GUI
        GUISimulator gui = new GUISimulator(width, height, Color.white);

        // Generate the rules for a Boids simulator
        GenericRule rule1 = new GenericRule(width, height, 50, (float) ((float) 135/ (float) 180*Math.PI), 30 ,new Range(0 ,200 ));
        GenericRule rule2 = new GenericRule(width, height, 100, (float) ((float) 135/ (float) 180*Math.PI), 7, new Range(200, 400));
        GenericRule rule3 = new GenericRule(width, height, 120, (float) ((float) 135/ (float) 180*Math.PI), 15, new Range(400, 600));
        // The boids using the rule1 are faster but their view distance is lower than the boids using the rule2
        // The boids using the rule3 see further and are a bit faster than those using rule2, but their eventIncrement will we higher (slower to react)


        float[][] placesToAvoid = new float[2][];
        placesToAvoid[0] = new float[2];
        placesToAvoid[0][0] = 100;
        placesToAvoid[0][1] = 100;
        placesToAvoid[1] = new float[2];
        placesToAvoid[1][0] = 700;
        placesToAvoid[1][1] = 700;

        // The boids using the rule4 avoid certain places.
        GenericRule rule4 = new GenericRule(width, height, 100, (float) ((float) 135/ (float) 180*Math.PI), 16, /*placesToAvoid,*/ new Range(600, 800));
        // A rule that makes a group of boids chase another group
        PredatorRule rulePredator = new PredatorRule(width, height, 100, (float) ((float) 135/ (float) 180*Math.PI), 5, new Range(800, 1_000), new Range(600, 800));


        // Create a random initial generation of Boids
        Generation gen = new Generation(width, height, 1_000);

        // Create the rules entries
        // Each rule will correspond to a certain number of boids, and an update interval
        List<MultiRuleEntry<Boid>> ruleTable = new ArrayList<>();
        ruleTable.add(new MultiRuleEntry<>(rule1, 1));
        ruleTable.add(new MultiRuleEntry<>(rule2, 3));
        ruleTable.add(new MultiRuleEntry<>(rule3, 1));
        ruleTable.add(new MultiRuleEntry<>(rule4, 1));
        ruleTable.add(new MultiRuleEntry<>(rulePredator, 1));

        // Create the display class
        BoidDisplay display = new BoidDisplay(ruleTable);

        // Build the automata
        MultiRuleSystem<Boid> automata = new MultiRuleSystem<>(gui, gen, ruleTable, display);
        gui.setSimulable(automata);
    }
}
