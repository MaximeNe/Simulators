package boids;

import agentsystem.IGeneration;

import java.lang.Math;
import java.util.ArrayList;

public class Generation implements IGeneration<Boid> {
    private final ArrayList<Boid> boidsList;

    public Generation(int width, int height, int numberOfBoids) {
        boidsList = new ArrayList<>();
        for (int i = 0; i < numberOfBoids; i++) {
            // Generate a random Boid
            boidsList.add(new Boid(new float[] {(float) Math.random()* width, (float) Math.random() * height}, new float[] {generateRandomSpeed(), generateRandomSpeed()}));
        }
    }

    public Generation(Generation other) {
        this.boidsList = new ArrayList<>(other.boidsList);
    }

    private float generateRandomSpeed() {
        if (Math.random()>0.5) {
            return (float) Math.random();
        }
        return (float) - Math.random();

    }

    @Override
    public IGeneration<Boid> clone() {
        return new Generation(this);
    }

    @Override
    public int nbAgents() {
        return boidsList.size();
    }
    @Override
    public Boid get(int i) {
        return boidsList.get(i);
    }
    @Override
    public void set(int i, Boid value) {
        boidsList.set(i, value);
    }
}
