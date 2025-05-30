package dk.sdu.cbse.common.entattributes;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

import static java.lang.Math.*;

public class EntMovement implements EntAttribute {
    private float yVelocity, xVelocity;
    private float deceleration, acceleration;
    private float speedMax, rotationSpeed;
    private boolean left, right, up;

    public EntMovement(float deceleration, float acceleration,
                       float speedMax, float rotationSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.speedMax = speedMax;
        this.rotationSpeed = rotationSpeed;
    }

    // getters and setter for movement of identities.
    public float getyVelocity() {
        return yVelocity;
    }
    public float getxVelocity() {
        return xVelocity;
    }
    public void setdeceleration(float deceleration) {
        this.deceleration = deceleration;
    }
    public void setacceleration(float acceleration) {
        this.acceleration = acceleration;
    }
    public void setspeedMax(float speedMax) {
        this.speedMax = speedMax;
    }
    public void setSpeed(float speed) {
        this.acceleration = speed;
        this.speedMax = speed;
    }
    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setUp() {
        this.up = up;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        EntPosition positionPart = entity.getPart(EntPosition.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float deltaTime = gameData.getDelta();

        // for left and right turning
        if (left) {
            radians += rotationSpeed * deltaTime;
        }
        if (right) {
            radians -= rotationSpeed * deltaTime;
        }

        // For the acceleration and deceleration
        if (up) {
            xVelocity += cos(radians) * acceleration * deltaTime;
            yVelocity += sin(radians) * acceleration * deltaTime;
        }

        float vec = (float) sqrt(xVelocity * deltaTime + yVelocity * yVelocity);
        if (vec > 0) {
            xVelocity -= (xVelocity/vec) * deceleration * deltaTime;
            yVelocity -= (yVelocity/vec) * deceleration * deltaTime;
        }
        if (vec > speedMax) {
            xVelocity = (xVelocity/vec) * speedMax;
            yVelocity = (yVelocity/vec) * speedMax;
        }

        // Sets the position
        x += xVelocity * deltaTime;
        if (x > gameData.getDisplayWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getDisplayWidth();
        }

        y += yVelocity * deltaTime;
        if (y > gameData.getDisplayHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getDisplayHeight();
        }
        positionPart.setX(x);
        positionPart.setY(y);
        positionPart.setRadians(radians);
    }

}
