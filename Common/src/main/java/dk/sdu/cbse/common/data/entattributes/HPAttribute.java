package dk.sdu.cbse.common.entattributes;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public class HPAttribute implements EntAttribute {
    private boolean killed = false;
    private int hp;
    private boolean isHit = false;

    // Getters, setters and instantiations of entity attributes
    public HPAttribute(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public boolean isHit() {
        return isHit;
    }
    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    public boolean isKilled() {
        return killed;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        // if entity with life is hit, hp is decreased by one.
        if (isHit) {
            hp = -1;
            isHit = false;
        }
        // checks if hp is zero, the entity is killed.
        if (hp <= 0) {
            killed = true;
        }
    }

}
