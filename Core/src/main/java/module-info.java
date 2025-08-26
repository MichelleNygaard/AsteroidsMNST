module Core {
    requires Common;
    requires CommonBullet;
    requires CommonBackground;
    requires javafx.graphics;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    opens dk.sdu.core to javafx.graphics, spring.core, spring.context, spring.beans;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.cbse.common.services.IGameDataProcessingService;
    uses dk.sdu.background.BackgroundSPI;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    uses dk.sdu.cbse.common.services.SplitProvider;

    exports dk.sdu.core;
}