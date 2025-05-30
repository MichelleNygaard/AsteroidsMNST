package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import java.util.Random;
import dk.sdu.cbse.common.data.entattributes.HPAttribute;
import dk.sdu.cbse.common.data.entattributes.EntMovement;
import dk.sdu.cbse.common.data.entattributes.EntPosition;


public class AstPlugin implements IGamePluginService {

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
        asteroid.add(new EntMovement(0, speed, speed, 0));
        asteroid.add(new EntPosition(30, 30, radians));
        asteroid.add(new HPAttribute(3));
        return asteroid;
    }
}
