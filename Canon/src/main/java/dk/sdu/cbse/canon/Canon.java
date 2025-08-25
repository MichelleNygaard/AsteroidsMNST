package dk.sdu.cbse.canon;


import java.util.ArrayList;
import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.weapon.Weapon;
import dk.sdu.cbse.common.weapon.WeaponSPI;

public class Canon extends Weapon implements  WeaponSPI {
    public Canon() {setWeaponName("Canon");}
    @Override
    public ArrayList<Entity> createShot(Entity shootingEntity, World world) {
        Bullets bigBullet = new Bullets();

        bigBullet.setPolygonCoordinates(8, -4, 8, 4, -8, 4, -8, -4);
        bigBullet.setRadius(8);
        double changeX = Math.cos(Math.toRadians(shootingEntity.getRotation()));
        double changeY = Math.sin(Math.toRadians(shootingEntity.getRotation()));
        bigBullet.setX(shootingEntity.getX() + changeX * 20);
        bigBullet.setY(shootingEntity.getY() + changeY * 20);

        bigBullet.setRotation(shootingEntity.getRotation());

        return new ArrayList<>() {{
            add(bigBullet);
        }};
    }
}