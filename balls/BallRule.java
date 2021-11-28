package balls;

import agentsystem.IGeneration;
import agentsystem.IRule;

import java.awt.*;

public class BallRule implements IRule<Ball> {
    private final int width;
    private final int height;
    private final int dx;
    private final int dy;

    public BallRule(int width, int height, int dx, int dy) {
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Translate one agent using dx and dy as the initial speed.
     * @param dx x translation
     * @param dy y translation
     */
    public Ball translate(Ball b, int dx, int dy) {
        Point p = new Point(b.position());
        Point speed = new Point(b.speed());

        // If initialisation:
        if (speed.x == 0) {
            if (dx > 0) {
                speed.x = 1;
            }
            else {
                speed.x = -1;
            }
            if (dy > 0) {
                speed.y = 1;
            }
            else {
                speed.y = -1;
            }
        }
        // Case x
        if (p.x + speed.x * dx >= width) {
            // Out of bound (right border)
            p.x = p.x + speed.x * dx - (width - p.x);
            speed.x *= -1; // Change direction
        }

        else if (p.x + speed.x * dx < 0) {
            // Out of bound (left border)
            p.x = -(p.x + speed.x * dx) + p.x;
            speed.x *= -1; // Change the direction
        }

        else {
            // Does fit in the GUI
            p.x += dx*speed.x;
        }

        // Case y
        if (p.y + speed.y * dy >= height) {
            // Out of bound (lower border)
            p.y = p.y + speed.y * dy - (height - p.y);
            speed.y *= -1; // Change direction
        }

        else if (p.y + speed.y * dy < 0) {

            // Out of bound (top border)
            p.y = -(p.y + speed.y * dy) + p.y;
            speed.y *= -1; // Change the direction
        }

        else {
            // Does fit in the GUI
            p.y += dy*speed.y;
        }

        return new Ball(p, speed);
    }

    @Override
    public void apply(int i, IGeneration<Ball> g, IGeneration<Ball> nextG) {
        Ball ball = g.get(i);
        Ball newBall = translate(ball, dx, dy);
        nextG.set(i, newBall);
    }
}
