package util;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void sub(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public void mult(Vector2 other) {
        this.x *= other.x;
        this.y *= other.y;
    }

    public void div(Vector2 other) {
        this.x /= other.x;
        this.y /= other.y;
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 sub(Vector2 a, Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    public static Vector2 mult(Vector2 a, Vector2 b) {
        return new Vector2(a.x * b.x, a.y * b.y);
    }

    public static Vector2 div(Vector2 a, Vector2 b) {
        return new Vector2(a.x / b.x, a.y / b.y);
    }
}
