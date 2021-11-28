package boids;

public class Boid {
    private float[] position;
    private float[] speed;

    public Boid(float[] position, float[] speed) {
        this.position = position;
        this.speed = speed;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public void setSpeed(float[] speed) {
        this.speed = speed;
    }

    public float[] getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Boid{position: (" + position[0] + " ; " + position[1] + ") speed: (" + speed[0] + " ; " + speed[1] + ")}";
    }
}
