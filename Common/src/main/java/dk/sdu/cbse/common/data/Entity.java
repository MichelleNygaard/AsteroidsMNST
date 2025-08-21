package dk.sdu.cbse.common.data;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class Entity {

    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private String color;
    private int healthPoints;

    public String getID() {return ID.toString();}

    public void setPolygonCoordinates(double... coordinates) { this.polygonCoordinates = coordinates;}
    public double[] getPolygonCoordinates() {return polygonCoordinates;}

    public void setX(double x) {this.x = x;}
    public double getX() {return x;}

    public void setY(double y) {this.y = y;}
    public double getY() {return y;}

    public void setRotation(double rotation) {this.rotation = rotation;}
    public double getRotation() {return rotation;}

    public void setRadius(float radius) {this.radius = radius;}
    public float getRadius() {return radius;}

    public void setColor(String color) {this.color = color;}
    public String getColor() {return color;}

    public void setHealthPoints(int healthPoints) {this.healthPoints = healthPoints;}
    public int getHealthPoints() {return healthPoints;}
}
