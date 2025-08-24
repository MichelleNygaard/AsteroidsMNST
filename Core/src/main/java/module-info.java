module Core {
    requires Common;
    requires CommonBullet;
    requires CommonBackground;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    opens dk.sdu.cbse to javafx.graphics;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    exports dk.sdu.cbse;
}