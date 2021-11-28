package boids;

import agentsystem.IGeneration;
import agentsystem.IRangeRule;
import agentsystem.IRule;
import util.Range;


import java.util.ArrayList;
import java.util.List;

/**
 * A generic boid rule. It can be used as a rule, but also as model to create more complex rules.
 *
 * See http://www.vergenet.net/~conrad/boids/pseudocode.html for the rules used.
 */
public class GenericRule implements IRangeRule<Boid> {
    private final int width; // width of the GUI
    private final int height; // height of the GUI
    private final float viewDistance; // max view distance for a boid to see other boids
    private final float viewAngle; // max view angle for a boid to see other boids
    private final float speedLimit; // the max speed of a boid
    private final Range range;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public GenericRule(int width, int height, float viewDistance, float viewAngle, float speedLimit, Range range) {
        this.width = width;
        this.height = height;
        this.viewAngle = viewAngle;
        this.viewDistance = viewDistance;
        this.speedLimit = speedLimit;
        this.range = range;
    }

    @Override
    public Range agentRange() {
        return this.range;
    }

    /**
     * Find all the neighbors of the Boid b using the parameters given when generating the rule.
     * @param allBoids The generation.
     * @param b The boid to find neighbors for.
     * @return List of boids
     */
    public ArrayList<Boid> findNeighbors (IGeneration<Boid> allBoids, Boid b) {
        ArrayList<Boid> neighbors = new ArrayList<Boid>();

        // Search through all the existing boids of the same group
        for (int i = range.start(); i < range.end(); i++) {
            Boid neigh = allBoids.get(i);
            if (neigh != b) {
                float x1 = b.getPosition()[0];
                float y1 = b.getPosition()[1];
                float x2 = neigh.getPosition()[0];
                float y2 = neigh.getPosition()[1];

                if (Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < viewDistance) { // Euclidian distance
                    // check if b can see neigh (direction)
                    float ux = b.getSpeed()[0];
                    float uy = b.getSpeed()[1];
                    float vx = x2 - x1;
                    float vy = y2 - y1;
                    float angle = (float) Math.acos((ux * vx + uy * vy) / (Math.sqrt(ux * ux + uy * uy) * Math.sqrt(vx * vx + vy * vy))); // Angle between the direction of the boid
                    if (angle < viewAngle) {
                        neighbors.add(neigh);
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Apply the rules explained by Parker and create a new Boid corresponding to the boid b in its next state
     * @param i The id of the agent.
     * @param gen The generation.
     * @param nextGeneration The next generation. This is the one you should modify.
     */
    @Override
    public void apply(int i, IGeneration<Boid> gen, IGeneration<Boid> nextGeneration) {
        Boid b = gen.get(i);

        // Apply the diferents rules using http://www.vergenet.net/~conrad/boids/pseudocode.html
        ArrayList<Boid> allBoids = new ArrayList<>();
        for (int j = 0; j < gen.nbAgents(); j++) {
            allBoids.add(gen.get(j));
        }
        ArrayList<Boid> neighbors = findNeighbors(gen, b);
        float[] v1 = rule1(b, neighbors);
        float[] v2 = rule2(b, allBoids, height, width);
        float[] v3 = rule3(b, neighbors);
        float[] v4 = boundPosition(b);

        // Update with the new speed and position
        float[] v = new float[2];
        v[0] = b.getSpeed()[0] + v1[0] + v2[0] + v3[0] + v4[0];
        v[1] = b.getSpeed()[1] + v1[1] + v2[1] + v3[1] + v4[1];

        v = limit_speed(v);

        float[] p = new float[2];
        p[0] = b.getPosition()[0] + v[0];
        p[1] = b.getPosition()[1] + v[1];

        nextGeneration.set(i, new Boid(p, v));
    }

    /**
     * Used to limit the speed of the boids => more realistic
     * @param previousVelocity The previous velocity of the boid.
     * @return the corrected velocity
     */
    public float[] limit_speed(float[] previousVelocity) {
        float velocity = (float) Math.sqrt(previousVelocity[0]*previousVelocity[0] + previousVelocity[1]*previousVelocity[1]);
        if (velocity > speedLimit) {
            float[] v = new float[2];
            v[0] = (previousVelocity[0] / velocity * speedLimit);
            v[1] = (previousVelocity[1] / velocity * speedLimit);
            return v;
        }
        return previousVelocity;
    }


    public float[] rule1(Boid bj, List<Boid> neighbors) {
        if (neighbors.size() == 0) {
            return bj.getSpeed();
        }

        float[] pc = new float[2];
        for (Boid b : neighbors) {
            pc[0] += b.getPosition()[0];
            pc[1] += b.getPosition()[1];
        }
        pc[0] = pc[0]/(neighbors.size());
        pc[1] = pc[1]/(neighbors.size());

        pc[0] = (pc[0] - bj.getPosition()[0])/100;
        pc[1] = (pc[1] - bj.getPosition()[1])/100;

        return pc;
    }


    /**
     * Returns the euclidian distance between b and bj
     * @param b a boid
     * @param bj another boid
     * @return the euclidian distance betweed the two boids
     */
    private float compute_distance(Boid b, Boid bj) {
        float x1 = b.getPosition()[0];
        float x2 = bj.getPosition()[0];
        float y1 = b.getPosition()[1];
        float y2 = bj.getPosition()[1];
        return (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }


    public float[] rule2(Boid bj, List<Boid> neighbors, int height, int width) {
        if (neighbors.size() == 0) {
            return bj.getSpeed();
        }

        // We consider that 2 boids are too close if they are closer than 1/50 of a characteristic size of the board (mean of height and width)
        float interBoidDist = ((float) height + (float) width)/1000;

        float facteur = 10;

        float[] c = new float[2];
        for (Boid b : neighbors) {
            if (compute_distance(b, bj) < interBoidDist) {
                c[0] += (b.getPosition()[0] - bj.getPosition()[0]) * facteur;
                c[1] += (b.getPosition()[1] - bj.getPosition()[1]) * facteur;
            }
        }
        return c;
    }


    public float[] rule3(Boid bj, List<Boid> neighbors) {
        if (neighbors.size() == 0) {
            return bj.getSpeed();
        }

        float[] pvj = new float[2];
        for (Boid b : neighbors) {
            pvj[0] += b.getSpeed()[0];
            pvj[1] += b.getSpeed()[1];
        }
        pvj[0] /= (neighbors.size());
        pvj[1] /= (neighbors.size());

        pvj[0] = (pvj[0] - bj.getSpeed()[0])/8;
        pvj[1] = (pvj[1] - bj.getSpeed()[1])/8;
        return pvj;
    }

    public float[] boundPosition(Boid b) {
        float[] v = new float[2];
        v[0] = b.getPosition()[0];
        v[1] = b.getPosition()[1];

        float[] c = new float[2]; // centre
        c[0] = width / 2;
        c[1] = height / 2;

        double distanceCentreCoin = Math.sqrt(Math.pow(c[0] - width, 2) + Math.pow(c[1] - height, 2));
        double distanceAuCentre = Math.sqrt((c[0] - v[0])*(c[0] - v[0]) + (c[1] - v[1])*(c[1] - v[1]));

        double distance = distanceAuCentre - distanceCentreCoin;
        if (distance < 0) {
            // le boids n'est pas sorti
            v[0] = 0;
            v[1] = 0;
            return v;
        }

        double facteur = 1.5;
        float expo = (float) ((float) Math.max(0, Math.exp(distance * facteur)));

        float x = b.getPosition()[0];
        float y = b.getPosition()[1];

        if (x < c[0]) {
            v[0] = expo;
        }
        else if (x > c[0]) {
            v[0] = - expo;
        }

        if (y < c[1]) {
            v[1] = expo;
        }
        else if (y > c[1]) {
            v[1] = - expo;
        }
        return v;
    }

}
