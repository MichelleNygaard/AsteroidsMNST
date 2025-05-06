package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControl implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        float screenWidth = gameData.getDisplayWidth();
        float screenHeight = gameData.getDisplayHeight();

        for (Entity bullet : world.getEntities(Bullets.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 6);
            bullet.setY(bullet.getY() + changeY * 6);

            if( bullet.getX() < 0 || bullet.getX() > screenWidth || bullet.getY() < 0 || bullet.getY() > screenHeight) {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullets();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);

//        bullet.setOwnerID(shooter.getID());

        return bullet;
    }
}