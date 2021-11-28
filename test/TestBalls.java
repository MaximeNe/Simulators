package test;

import agentsystem.*;
import balls.Ball;
import balls.BallDisplay;
import balls.BallGeneration;
import balls.BallRule;
import gui.GUISimulator;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The BallsSimulator class using 3 points
 */
public class TestBalls {
    public static void main (String [] args) {
        int width = 500;
        int height = 500;

        ArrayList<Point> balls = new ArrayList<>(Arrays.asList(new Point(30, 40), new Point(10, 150), new Point(350, 488),
                new Point(25, 120), new Point(200, 200), new Point(300, 450), new Point(80, 450), new Point(450, 60),
                new Point(250, 10), new Point(120, 360), new Point(350, 120), new Point(300, 42), new Point(42, 42),
                new Point(80, 52), new Point(180, 30), new Point(450, 200), new Point(400, 360), new Point(82, 96)));
        BallGeneration gen = new BallGeneration(balls);
        BallRule rule = new BallRule(width, height, 5, 5);
        GUISimulator gui = new GUISimulator(width, height, Color.GRAY);
        BallDisplay display = new BallDisplay();
        IAgentSystem<Ball> ballSystem = new SingleRuleSystem<>(gui, gen, rule, display);

        gui.setSimulable(ballSystem);
    }
}
