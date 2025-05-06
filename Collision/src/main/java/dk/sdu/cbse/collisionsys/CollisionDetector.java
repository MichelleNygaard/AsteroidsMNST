package dk.sdu.cbse.collisionsys;
import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
//        for (Entity enemy : world.getEntities(Enemy.class)) {
//            for (Entity bullet : world.getEntities(Bullets.class)) {
//                if (bullet.getOwnerID() != null && bullet.getOwnerID().equals(enemy.getID())) {
//                    continue;
//                }
//
//                if (collides(enemy, bullet)) {
//                    world.removeEntity(enemy);
//                    world.removeEntity(bullet);
//                }
//
//            }
//        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}

