module CommonBackground {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires java.desktop;
    exports dk.sdu.background;
    uses dk.sdu.background.BackgroundSPI;
}