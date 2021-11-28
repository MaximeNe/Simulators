package boids;


import gui.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Triangle implements GraphicalElement {
    private Color drawColor;
    private Color fillColor;

    // Coordinates of the triangle
    private float x;
    private float y;

    // a direction vector
    private float dx;
    private float dy;

    private float height;



    public Triangle(float x, float y, Color drawColor, Color fillColor, float height, float dx, float dy) {
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.x = x;
        this.y = y;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
    }


    public void paint(Graphics2D g2d) {
        Stroke currentStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0F));
        Color current = g2d.getColor();

        // The coordinates of the vertices of the triangle we want to draw
        int xArray[] = new int[3];
        int yArray[] = new int[3];

        // Some math to find the vertices of the triangle

        double angle;
        if (dy > 0) {
            angle = -Math.acos(dx/(Math.sqrt(dx*dx + dy*dy)));
        }
        else {
            angle = Math.acos(dx/(Math.sqrt(dx*dx + dy*dy)));
        }
        xArray[0] = (int) (x+height*Math.cos(angle));
        yArray[0] = (int) (y-height*Math.sin(angle));

        xArray[1] = (int) (x+height*0.3*Math.cos(angle + Math.PI/3));
        yArray[1] = (int) (y-height*0.3*Math.sin(angle + Math.PI/3));

        xArray[2] = (int) (x+height*0.3*Math.cos(angle - Math.PI/3));
        yArray[2] = (int) (y-height*0.3*Math.sin(angle - Math.PI/3));

        // Plot the triangle
        if (this.fillColor != null) {
            g2d.setColor(this.fillColor);
            g2d.fillPolygon(xArray,yArray,3);
        }

        g2d.setColor(this.drawColor);
        g2d.drawPolygon(xArray,yArray,3);

        g2d.setColor(current);
        g2d.setStroke(currentStroke);
    }

    public String toString() {
        return this.drawColor.toString() + " triangle";
    }
}
