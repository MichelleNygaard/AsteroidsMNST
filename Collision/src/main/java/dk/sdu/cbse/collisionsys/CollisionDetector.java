package dk.sdu.cbse.collisionsys;

import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {
    private final IAsteroidsSplitter asteroidSplitter = ServiceLoader.load(IAsteroidSplitter.class)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Can't find IAsteroidSplitter implementation"));

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) throws IOException, URISyntaxException, InterruptedException {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                //Check if the two entities are identical, and skips if they are
                if (entity1.getId().equals(entity2.getID())) {
                    continue;
                }

                if (entity1 instanceof Asteroids && entity2 instanceof Asteroids) {
                    continue;
                }

                // The collision detection
                if (this.collides(entity1, entity2)) {
                    System.out.println(entity1.getClass());
                    System.out.println(entity2.getClass());
                    if (entity1.getClass().equals(Bullets.class) && entity2.getClass().equals(Asteroids.class)) {
                        world.removeEntity(entity1);
                        asteroidSplitter.createSplitAsteroid(entity2, world, gameData);
                    } else if (entity1.getClass().equals(Asteroids.class) && entity2.getClass().equals(Bullets.class)) {
                        world.removeEntity(entity2);
                        asteroidSplitter.createSplitAsteroid(entity1, world, gameData);
                    } else {
                        world.removeEntity(entity1);
                        world.removeEntity(entity2);
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX - (float) entity2.getX();
        float dy = (float) entity1.getY - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}

