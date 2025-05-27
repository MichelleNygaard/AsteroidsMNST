package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.collisionsys.CollisionDetector;
import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

import java.util.Random;

public class AstSplitterImpl implements IAsteroidsSplitter {

    private final Random random = new Random();

    @Override
    public void createSplitAsteroid(Entity asteroid, World world) {
        float originalSize = asteroid.getRadius();

        // Only splits the asteroid if it is above a certain size. 
        if (originalSize > 10) {
            float newSize = originalSize / 2;

            // code for creating the two smaller asteroids 
            for (int i = 0; i < 2; i++) {
                Asteroids newAsteroids = new Asteroids();
                newAsteroids.setRadius(newSize);

                // Setting random direction and position offset
                double angle = random.nextDouble() * 2 * Math.PI;
                double offsetX = Math.cos(angle) * (newSize + 5);
                double offsetY = Math.sin(angle) * (newSize + 5);

                newAsteroids.setX(asteroid.getX() + offsetX);
                newAsteroids.setY(asteroid.getY() + offsetY);

                // Random roation ofr movement
                newAsteroids.setRotation(random.nextInt(360));

                world.addEntity(newAsteroids);
            }
        }
        world.removeEntity(asteroid);
    }
}
