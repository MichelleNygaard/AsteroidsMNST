package dk.sdu.cbse.common.weapon;

import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.util.ArrayList;
import java.util.List;

public interface WeaponSPI {
    ArrayList<Entity> createShot(Entity shootingEntity, World world);
}
