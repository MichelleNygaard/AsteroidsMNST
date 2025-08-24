package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;
    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }


    // Creates an enermy ship at a random location with a random size.
    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        Random rnd = new Random();
        int size = rnd.nextInt(6) + 4;
        enemyShip.setPolygonCoordinates(-size, -size, size, size, -size, size);
        enemyShip.setX(rnd.nextInt(gameData.getDisplayWidth()));
        enemyShip.setY(rnd.nextInt(gameData.getDisplayHeight()));
        enemyShip.setRadius(15);
        enemyShip.setRotation(rnd.nextInt(90));

        return enemyShip;
    }
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
            world.removeEntity(enemy);
    }
}