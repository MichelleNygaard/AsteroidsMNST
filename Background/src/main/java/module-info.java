import dk.sdu.cbse.BackgroundPlug;
import javafx.scene.layout.Background;

module Background {
    requires Common;
    requires CommonBackground;
    requires spring.context;
    requires javafx.graphics;
    requires java.desktop;
    provides dk.sdu.cbse.BackgroundPlug with dk.sdu.cbse.BackgroundPlug;
    exports dk.sdu.cbse;
}