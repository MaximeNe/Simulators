package test;

import agentsystem.SingleRuleSystem;
import cellular.GridDisplay;
import cellular.conway.ConwayCell;
import cellular.conway.ConwayGeneration;
import cellular.conway.ConwayRule;
import gui.GUISimulator;

import java.awt.Point;
import java.awt.Color;
import java.util.HashMap;

public class TestConwaySimulator {
    public static void main(String[] args) {
        int height = 10;
        int width = 10;
        int scale = 30;

        GUISimulator gui = new GUISimulator(width * scale,
                height * scale, Color.white);

        HashMap<Point, Integer> startingCells = new HashMap<>();
        startingCells.put(new Point(2, 1), ConwayCell.ALIVE);
        startingCells.put(new Point(2, 2), ConwayCell.ALIVE);
        startingCells.put(new Point(2, 3), ConwayCell.ALIVE);
        startingCells.put(new Point(2, 6), ConwayCell.ALIVE);
        startingCells.put(new Point(2, 7), ConwayCell.ALIVE);
        startingCells.put(new Point(3, 6), ConwayCell.ALIVE);
        startingCells.put(new Point(3, 7), ConwayCell.ALIVE);
        startingCells.put(new Point(9, 3), ConwayCell.ALIVE);
        startingCells.put(new Point(7, 2), ConwayCell.ALIVE);
        startingCells.put(new Point(8, 2), ConwayCell.ALIVE);
        startingCells.put(new Point(7, 1), ConwayCell.ALIVE);
        startingCells.put(new Point(8, 4), ConwayCell.ALIVE);
        startingCells.put(new Point(7, 7), ConwayCell.ALIVE);
        startingCells.put(new Point(7, 8), ConwayCell.ALIVE);
        startingCells.put(new Point(8, 7), ConwayCell.ALIVE);
        startingCells.put(new Point(9, 9), ConwayCell.ALIVE);

        ConwayCell cellType = new ConwayCell();
        ConwayGeneration gen = new ConwayGeneration(width, height, startingCells);
        ConwayRule rule = new ConwayRule();
        GridDisplay display = new GridDisplay(cellType, scale);
        SingleRuleSystem<Integer> automata = new SingleRuleSystem<>(gui, gen, rule, display);

        gui.setSimulable(automata);
    }
}
