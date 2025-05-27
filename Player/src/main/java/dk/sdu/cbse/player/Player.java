package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity {
    // Lab exercise 2: Hit points to allow multiple hits before dying.
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
