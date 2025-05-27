package dk.sdu.cbse.common.enemy;

import dk.sdu.cbse.common.data.Entity;

public class Enemy extends Entity {
    // Lab 2 exercise: hit points to allow multiple hits before enemy is defeated
    private int hitPoints = 3;

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void decrementHitPoints() {
        this.hitPoints--;
    }
}
