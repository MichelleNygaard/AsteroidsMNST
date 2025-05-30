package dk.sdu.cbse.common.data.entattributes;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public class EntTimer  implements EntAttribute {
    private float expiration;
    public EntTimer(float expiration) {
        this.expiration = expiration;
    }
    public float getExpiration() {
        return expiration;
    }
    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }
    public void reduceExpiration(float delta) {
        this.expiration -= delta;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (expiration > 0) {
            reduceExpiration(gameData.getDelta());
        }
        if (expiration <= 0) {
            HPAttribute hpAttribute = entity.getPart(HPAttribute.class);
            hpAttribute.setHp(0);
        }
    }
}
