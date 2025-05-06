import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires Player;
    requires CommonEnemy;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemy.EnemyProcessor;

}