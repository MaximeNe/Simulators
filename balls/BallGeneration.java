package balls;

import agentsystem.IGeneration;

import java.awt.*;
import java.util.ArrayList;


/**
 * Implementation of a Balls class
 * Balls is used to simulate points in movement in a 2D environment
 */
public class BallGeneration implements IGeneration<Ball> {
    private final ArrayList<Ball> balls;
    // x and y can be -1, 0, 1.
    // a positive speed in x means that the point is going to the right edge.
    // a positive speed in y means that the point is going down.
    public BallGeneration(ArrayList<Point> initialBalls) {
        this.balls = new ArrayList<>();
        for (Point p : initialBalls) {
            balls.add(new Ball(new Point(p), new Point(0, 0)));
        }
    }

    public BallGeneration(BallGeneration other) {
        this.balls = new ArrayList<>(other.balls);
    }

    @Override
    public BallGeneration clone() {
        return new BallGeneration(this);
    }
    @Override
    public int nbAgents() {
        return balls.size();
    }
    @Override
    public Ball get(int i) {
        return balls.get(i);
    }
    @Override
    public void set(int i, Ball value) {
        balls.set(i, value);
    }
}
