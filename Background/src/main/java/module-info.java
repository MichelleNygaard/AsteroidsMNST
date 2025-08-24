import dk.sdu.cbse.BackgroundPlug;
import javafx.scene.layout.Background;

module Background {
    requires Common;
    requires CommonBackground;
    requires spring.context;
    requires javafx.graphics;
    requires java.desktop;
    provides BackgroundPlug with BackgroundPlug;
    exports dk.sdu.cbse;
}