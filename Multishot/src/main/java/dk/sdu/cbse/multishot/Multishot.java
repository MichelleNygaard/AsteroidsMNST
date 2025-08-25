package dk.sdu.cbse.multishot;


import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.weapon.Weapon;
import dk.sdu.cbse.common.weapon.WeaponSPI;

import java.util.ArrayList;

public class Multishot extends Weapon implements WeaponSPI {
    public Multishot() {
        setWeaponName("Multishot");
    }

    @Override
    public ArrayList<Entity> createShot(Entity shootingEntity, World world) {
        Bullets bullet1 = new Bullets();
        Bullets bullet2 = new Bullets();
        Bullets bullet3 = new Bullets();

        bullet1.setRotation(shootingEntity.getRotation() - 45);
        bullet2.setRotation(shootingEntity.getRotation());
        bullet3.setRotation(shootingEntity.getRotation() + 45);

        ArrayList<Entity> bullets = new ArrayList<>() {{
            add(bullet1);
            add(bullet2);
            add(bullet3);
        }};

        for (Entity bullet : bullets) {
            bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
            double changeX = Math.cos(Math.toRadians(shootingEntity.getRotation()));
            double changeY = Math.sin(Math.toRadians(shootingEntity.getRotation()));
            bullet.setX(shootingEntity.getX() + changeX * 10);
            bullet.setY(shootingEntity.getY() + changeY * 10);
            bullet.setRadius(1);
        }
        return bullets;
    }
}
