import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    exports dk.sdu.cbse.player;
    requires Common;
    requires CommonBullet;
    requires spring.context;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    uses IGamePluginService;
    provides IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.player.PlayerControl;

}