module CommonBackground {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    exports dk.sdu.background;
    uses dk.sdu.background.BackgroundSPI;
}