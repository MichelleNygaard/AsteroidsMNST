import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    exports dk.sdu.cbse.player;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.player.PlayerControl;

}