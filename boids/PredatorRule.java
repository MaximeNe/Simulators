package boids;

import agentsystem.IGeneration;
import util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * A rule for predators to follow their prey.
 */
public class PredatorRule extends GenericRule {
    private Range preyRange;
    private final int width = getWidth();
    private final int height = getHeight();

    public PredatorRule(int width, int height, float viewDistance, float viewAngle,
                        float speedLimit, Range range, Range preyRange) {
        super(width, height, viewDistance, viewAngle, speedLimit, range);
        this.preyRange = preyRange;
    }

    @Override
    public void apply(int i, IGeneration<Boid> gen, IGeneration<Boid> nextGeneration) {
        Boid b = gen.get(i);

        // Apply the diferents rules using http://www.vergenet.net/~conrad/boids/pseudocode.html
        List<Boid> neighbors = findNeighbors(gen, b);
        float[] v1 = rule1(b, neighbors);
        float[] v2 = rule2(b, neighbors, height, width);
        float[] v3 = rule3(b, neighbors);
        List<Boid> preyList = new ArrayList<>();
        for (int j = preyRange.start(); j < preyRange.end(); j++) {
            preyList.add(gen.get(i));
        }
        float[] v4 = chase(b, i, gen);
        float[] v5 = boundPosition(b);

        // Update with the new speed and position
        float[] v = new float[2];
        v[0] = b.getSpeed()[0] + v1[0] + v2[0] + v3[0] + v4[0] + v5[0];
        v[1] = b.getSpeed()[1] + v1[1] + v2[1] + v3[1] + v4[1] + v5[1];

        v = limit_speed(v);

        float[] p = new float[2];
        p[0] = b.getPosition()[0] + v[0];
        p[1] = b.getPosition()[1] + v[1];

        nextGeneration.set(i, new Boid(p, v));
    }

    public float[] chase(Boid b, int i, IGeneration<Boid> gen) {
        // Each boid chases a corresponding prey
        Boid prey = gen.get((preyRange.start() + i) % preyRange.end());
        float preyX = prey.getPosition()[0];
        float preyY = prey.getPosition()[1];

        float[] v = new float[2];
        v[0] = (preyX - b.getPosition()[0]) / 10;
        v[1] = (preyY - b.getPosition()[1]) / 10;
        return v;
    }
}
