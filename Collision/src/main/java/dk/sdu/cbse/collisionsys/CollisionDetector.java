package dk.sdu.cbse.collisionsys;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.player.Player;

public class CollisionDetector implements IPostEntityProcessingService {

    private IAsteroidsSplitter asteroidsSplitter;

    public CollisionDetector() {
        // Default constructor
    }

    public void setAsteroidsSplitter(IAsteroidsSplitter asteroidsSplitter) {
        this.asteroidsSplitter = asteroidsSplitter;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }
                if (this.collides(entity1, entity2)) {

                    // lab 2 exercise: Player/Enemy is only destroyed when hitpoints reach 0.
                    if (entity1 instanceof Player && entity2 instanceof Bullets) {
                        Player player = (Player) entity1;
                        player.decrementHitPoints();
                        world.removeEntity(entity2); // removes bullet
                        if (player.getHitPoints() <= 0) {
                            world.removeEntity(player);
                        }
                        continue;
                    }
                    if (entity2 instanceof Player && entity1 instanceof Bullets) {
                        Player player = (Player) entity2;
                        player.decrementHitPoints();
                        world.removeEntity(entity1);
                        if (player.getHitPoints() <= 0) {
                            world.removeEntity(player);
                        }
                        continue;
                    }
                    if (entity1 instanceof Enemy && entity2 instanceof Bullets) {
                        Enemy enemy = (Enemy) entity1;
                        enemy.decrementHitPoints();
                        world.removeEntity(entity2); // removes bullet
                        if (enemy.getHitPoints() <= 0) {
                            world.removeEntity(enemy);
                        }
                        continue;
                    }
                    if (entity2 instanceof Enemy && entity1 instanceof Bullets) {
                        Enemy enemy = (Enemy) entity2;
                        enemy.decrementHitPoints();
                        world.removeEntity(entity1);
                        if (enemy.getHitPoints() <= 0) {
                            world.removeEntity(enemy);
                        }
                        continue;
                    }

                    // Check if one of the entities is an asteroid
                    if (entity1 instanceof Asteroids) {
                        asteroidsSplitter.createSplitAsteroid(entity1, world);
                    }
                    if (entity2 instanceof Asteroids) {
                        asteroidsSplitter.createSplitAsteroid(entity2, world);
                    }

                    // Remove both entities
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}

