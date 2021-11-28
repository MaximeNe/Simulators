package boids;

import agentsystem.IGeneration;
import util.Range;

import java.util.ArrayList;

/**
 * A more complex set of rules, where boids avoid certain positions
 */
public class AvoidPlaceRule extends GenericRule {

    private float viewDistance = getViewDistance();
    private final float[][] places;
    private final int width = getWidth();
    private final int height = getHeight();

    public AvoidPlaceRule(int width, int height, float viewDistance, float viewAngle, float speedLimit, float[][] places, Range range) {
        super(width, height, viewDistance, viewAngle, speedLimit, range);
        this.places = places;

    }

    private float[] tendToPlace(Boid b, float[] place) {
        // If too far awy, do noting
        float[] output = new float[2];
        output[0] = (place[0] - b.getPosition()[0]) / 100;
        output[1] = (place[1] - b.getPosition()[1]) / 100;
        // If too far awy, do noting
        if (output[0] > viewDistance/100  || output[0] < -viewDistance/100 || output[1] > viewDistance/100 || output[1] < -viewDistance/100) {
            return new float[2];
        }
        return output;
    }


    /**
     * A rule to avoid certain places.
     * A more realistic way of implementing this rule would be to use a decreasing exponential function with the distance
     * the place to avoid as parameter of the exponential.
     * @param b the boid we apply the rule for
     * @return a velocity vector
     */
    private float[] rule4(Boid b) {
        if (places.length == 0) {
            return new float[2];
        }
        float[] v = new float[2];
        for (float[] place : places) {
            float[] v2 = tendToPlace(b, place);
            v[0] += v2[0];
            v[1] += v2[1];
        }
        v[0] = (float) -5*v[0];
        v[1] = (float) -5*v[1];
        return v;
    }


    @Override
    public void apply(int i, IGeneration<Boid> gen, IGeneration<Boid> nextGeneration) {
        Boid b = gen.get(i);

        // Apply the diferents rules using http://www.vergenet.net/~conrad/boids/pseudocode.html
        ArrayList<Boid> neighbors = findNeighbors(gen, b);
        float[] v1 = rule1(b, neighbors);
        float[] v2 = rule2(b, neighbors, height, width);
        float[] v3 = rule3(b, neighbors);
        float[] v4 = rule4(b);
        float[] v5 = boundPosition(b);


        // Update with the new speed and position
        float[] v = new float[2];
        v[0] = b.getSpeed()[0] + v1[0] + v2[0] + v3[0] + v4[0] + v5[0];
        v[1] = b.getSpeed()[1] + v1[1] + v2[1] + v3[1] + v4[1] + v5[0];

        v = limit_speed(v);

        float[] p = new float[2];
        p[0] = b.getPosition()[0] + v[0];
        p[1] = b.getPosition()[1] + v[1];

        nextGeneration.set(i, new Boid(p, v));
    }
}