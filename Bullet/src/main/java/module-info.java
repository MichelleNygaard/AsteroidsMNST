import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides BulletSPI with dk.sdu.cbse.bullet.BulletControl;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletControl;
}