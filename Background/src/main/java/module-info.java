import dk.sdu.cbse.BackgroundPlug;
import javafx.scene.layout.Background;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.background.BackgroundSPI;

module Background {
    requires Common;
    requires CommonBackground;
    requires spring.context;
    requires javafx.graphics;
    provides dk.sdu.background.BackgroundSPI with dk.sdu.cbse.BackgroundPlug;
    provides IGamePluginService with dk.sdu.cbse.BackgroundPlug;
    exports dk.sdu.cbse;
}