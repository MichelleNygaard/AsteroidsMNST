package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;


public class AstProcessor implements IEntityProcessingService {

        @Override
        public void process(GameData gameData, World world) {
            for (Entity asteroid : world.getEntities(Asteroids.class)) {
                double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
                double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

                asteroid.setX(asteroid.getX() + changeX * 0.5);
                asteroid.setY(asteroid.getY() + changeY * 0.5);

                if (asteroid.getX() < 0) {
                    asteroid.setX(asteroid.getX() - gameData.getDisplayWidth());
                }

                if (asteroid.getX() > gameData.getDisplayWidth()) {
                    asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
                }

                if (asteroid.getY() < 0) {
                    asteroid.setY(asteroid.getY() - gameData.getDisplayHeight());
                }

                if (asteroid.getY() > gameData.getDisplayHeight()) {
                    asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
                }

            }
        }
    }


