package dk.sdu.background;

import dk.sdu.cbse.common.data.GameData;

import javafx.scene.image.ImageView;

public interface BackgroundSPI {
    ImageView getBackground(GameData gamedata);
}
