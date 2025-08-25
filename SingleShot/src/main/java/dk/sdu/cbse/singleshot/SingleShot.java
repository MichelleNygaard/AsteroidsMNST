package dk.sdu.cbse.singleshot;


import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.weapon.WeaponSPI;

import java.util.ArrayList;
public class SingleShot implements WeaponSPI {
    @Override
    public ArrayList<Entity> createShot(Entity shootingEntity, World world) {
        Bullets bullet = new Bullets();

        bullet.setRotation(shootingEntity.getRotation());
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shootingEntity.getRotation()));
        double changeY = Math.sin(Math.toRadians(shootingEntity.getRotation()));
        bullet.setX(shootingEntity.getX() + changeX * 10);
        bullet.setY(shootingEntity.getY() + changeY * 10);
        bullet.setRadius(1);

        return new ArrayList<>() {{
            add(bullet);
        }};
    }
}
