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
        Random rnd = new Random();
        for(int i = 0; i< rnd.nextInt(2,10); i++) {
            // Adds asteroid entities to the world
            Entity asteroid = createAsteroid(gameData);
            asteroid.setHealthPoints(rnd.nextInt(2,4));
            world.addEntity(asteroid);
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove asteroid enteties
        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Asteroids asteroid = new Asteroids();
        Random rnd = new Random();

        //Set the size and placement of asteroids
        int size = rnd.nextInt(15) + 8;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(0, gameData.getDisplayWidth()));
        asteroid.setY(rnd.nextInt(0, gameData.getDisplayHeight()));
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHealthPoints(rnd.nextInt(1,4));
        return asteroid;
    }
}
