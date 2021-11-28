package test;

import agentsystem.SingleRuleSystem;
import cellular.GridDisplay;
import cellular.immigration.ImmigrationCell;
import cellular.immigration.ImmigrationGeneration;
import cellular.immigration.ImmigrationRule;

import gui.GUISimulator;

import java.awt.Point;
import java.lang.Math;
import java.awt.Color;
import java.util.HashMap;

public class TestImmigrationGame {
    public static void main(String[] args) {
        int height = 20;
        int width = 20;
        int scale = 30;

        int maxState = 4;

        GUISimulator gui = new GUISimulator(width * scale,
                height * scale, Color.white);

        HashMap<Point, Integer> startingCells = new HashMap<>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                startingCells.put(new Point(i, j), (int)(Math.random()*(maxState+1)));
            }
        }


        ImmigrationGeneration gen = new ImmigrationGeneration(width, height, startingCells);
        ImmigrationRule rule = new ImmigrationRule(maxState);
        ImmigrationCell cellType = new ImmigrationCell(maxState);
        GridDisplay display = new GridDisplay(cellType, scale);
        SingleRuleSystem<Integer> automata = new SingleRuleSystem<>(gui, gen, rule, display);

        gui.setSimulable(automata);
    }
}
