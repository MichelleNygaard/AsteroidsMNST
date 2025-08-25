module Core {
    requires Common;
    requires CommonBullet;
    requires CommonBackground;
    requires javafx.graphics;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    opens dk.sdu.core to javafx.graphics;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.background.BackgroundSPI;

    exports dk.sdu.core;
}