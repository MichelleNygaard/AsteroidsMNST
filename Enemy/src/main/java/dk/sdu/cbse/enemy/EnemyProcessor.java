package dk.sdu.cbse.enemy;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.player.Player;


public class EnemyProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);
        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (player != null) {
                // This is the arctan2 formula to triangulate the angle between 2 points
                // I use it for setting the rotation of the enemy, so that it always points at the player
                double deltaX = player.getX() - enemy.getX();
                double deltaY = player.getY() - enemy.getY();

                double rotationAngle = Math.atan2(deltaY, deltaX);
                enemy.setRotation(Math.toDegrees(rotationAngle));

                // Sets the enemy to move
                double changeX = Math.cos(Math.toRadians(enemy.getRotation())) * 0.7; // Enemy should be a bit slower than player
                double changeY = Math.sin(Math.toRadians(enemy.getRotation())) * 0.7;
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
            }


            //Stop the enemy if it hits the display border
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth()-1);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight()-1);
            }


        }
    }
}
