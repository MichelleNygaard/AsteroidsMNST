package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AstPlugin implements IGamePluginService {
    private Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        // Adds asteroid entities to the world
        Entity asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove asteroid enteties
        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroids();
        float radians = (float) Math.random() * 2 * 3.1415f;
        float speed = (float) Math.random() * 10f + 20f;

        //Set the size of asteroids
        asteroid.setRadius(20);
        return asteroid;
    }
}
