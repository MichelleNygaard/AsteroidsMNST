package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.entattributes.EntMovement;
import dk.sdu.cbse.common.data.entattributes.EntPosition;
import dk.sdu.cbse.common.data.entattributes.HPAttribute;
import dk.sdu.cbse.common.services.IEntityProcessingService;


public class AstProcessor implements IEntityProcessingService {
    private IAsteroidsSplitter astSplitter = new AstSplitterImpl();

        @Override
        public void process(GameData gameData, World world) {
            for (Entity asteroid : world.getEntities(Asteroids.class)) {
                EntPosition entPosition = asteroid.getPart(EntPosition.class);
                EntMovement entMovement = asteroid.getPart(EntMovement.class);
                HPAttribute hpAttribute = asteroid.getPart(HPAttribute.class);

                int numPoint = 12;
                float speed = (float) Math.random() * 10f + 20f;
                if (hpAttribute.getHp() == 1) {
                    numPoint = 8;
                    speed = (float) Math.random() * 30f + 70f;
                } else if (hpAttribute.getHp() == 2) {
                    numPoint = 10;
                    speed = (float) Math.random() * 10f + 50f;
                }
                entMovement.setSpeed(speed);
                entMovement.setUp(true);

                entMovement.process(gameData, asteroid);
                entPosition.process(gameData, asteroid);

                // In event of splitting
                if (hpAttribute.isHit()) {
                    astSplitter.createSplitAsteroid(asteroid, world);
                }
                setShape(asteroid, numPoint);
            }

        }

        /**
         * Dependency Injection using OSGi Declarative Services
         */
        public void setAsteroidSplitter(IAsteroidsSplitter asteroidSplitter) {
            this.astSplitter = asteroidSplitter;
        }

        public void removeAsteroidSplitter(IAsteroidsSplitter asteroidSplitter) {
            this.astSplitter = null;
        }

        private void setShape(Entity entity, int numPoint) {
            EntPosition position = entity.getPart(EntPosition.class);
            float[] shapex = new float[numPoint];
            float[] shapey = new float[numPoint];
            float radians = position.getRadians();
            float x = position.getX();
            float y = position.getY();
            float radius = entity.getRadius();

            float angle = 0;

            for (int i = 0; i < numPoint; i++) {
                shapex[i] = x + (float) Math.cos(angle + radians) * radius;
                shapey[i] = y + (float) Math.sin(angle + radians) * radius;
                angle += 2 * 3.1415f / numPoint;
            }
            entity.setShapeX(shapex);
            entity.setShapeY(shapey);
        }
    }


