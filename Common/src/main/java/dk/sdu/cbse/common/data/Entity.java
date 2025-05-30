package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.entattributes.EntAttribute;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float radius;
    private Map<Class, EntAttribute> parts;


    public Entity() {
        parts = new ConcurrentHashMap<>();
    }
    public void add(EntAttribute part) {
        parts.put(part.getClass(), part);
    }
    public void remove(Class partClass) {
        parts.remove(partClass);
    }
    public <E extends EntAttribute> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
    public float getRadius() {
        return this.radius;
    }
    public String getID() {
        return ID.toString();
    }
    public float[] getShapeX(float[] shapeX) {
        return shapeX;
    }
    public float[] getShapeY(float[] shapeY) {
        return shapeY;
    }
    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }
    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }
}
