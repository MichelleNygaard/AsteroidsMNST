import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;
    requires CommonAsteroids;
    requires Player;
    provides IPostEntityProcessingService with dk.sdu.cbse.collisionsys.CollisionDetector;
    exports dk.sdu.cbse.collisionsys;
}