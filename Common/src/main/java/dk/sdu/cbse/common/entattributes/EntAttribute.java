package dk.sdu.cbse.common.entattributes;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;


public interface EntAttribute {
    void process (GameData gameData, Entity ent);
}
